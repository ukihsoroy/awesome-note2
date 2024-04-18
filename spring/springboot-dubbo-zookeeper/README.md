###SpringBoot Dubbo xml简单配置

**1.构建两个简单的SpringBoot工程, 引入如下依赖**

```
    <!--SpringBoot 父依赖 这里使用的是1.5.9版本-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
        <relativePath/>
    </parent>
    
    <dependencies>
        <!--SpringBoot 快速启动-->
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter</artifactId>
        </dependency>
    
        <!--测试模块-->
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
    
        <!--内嵌Tomcat-->
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-tomcat</artifactId>
        </dependency>
    
        <!--Dubbo 服务治理-->
        <dependency>
          <groupId>com.alibaba</groupId>
          <artifactId>dubbo</artifactId>
          <version>2.5.8</version>
          <exclusions>
            <exclusion>
              <artifactId>spring</artifactId>
              <groupId>org.springframework</groupId>
            </exclusion>
          </exclusions>
        </dependency>
    
        <!--Zookeeper 依赖-->
        <dependency>
          <groupId>org.apache.zookeeper</groupId>
          <artifactId>zookeeper</artifactId>
          <version>3.5.3-beta</version>
          <exclusions>
            <exclusion>
              <groupId>org.slf4j</groupId>
              <artifactId>slf4j-log4j12</artifactId>
            </exclusion>
            <exclusion>
              <groupId>log4j</groupId>
              <artifactId>log4j</artifactId>
            </exclusion>
          </exclusions>
        </dependency>
    
        <!--Zookeeper连接客户端-->
        <dependency>
          <groupId>com.github.sgroschupf</groupId>
          <artifactId>zkclient</artifactId>
          <version>0.1</version>
        </dependency>
    </dependencies>
```

**2.创建API模块-统一管理接口和DTO对象**

- pom配置
```
    <groupId>org.ko</groupId>
    <artifactId>api</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
```

**3.配置服务提供方-注意版本号**

- pom中引入API jar依赖
```
    <dependencies>
        <dependency>
            <groupId>org.ko</groupId>
            <artifactId>api</artifactId>
            <version>1.0.0</version>
        </dependency>
    </dependencies>
```

- yml文件中确定端口好
```
    server:
      port: 8080  #指定端口号为8080 区分端口
```
- 使用xml启动dubbo, 在resources/dubbo/config.xml中, 引入统一管理的提供接口
```
    <!--Dubbo的服务名称-->
    <dubbo:application name="dubbo-provider"/>

    <!-- dubbo指定注册中心zookeeper和地址-->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>

    <!--引入统一维护的服务暴露接口-->
    <import resource="provider.xml"/>
```
- 配置对外暴露服务的接口, 实例是由Spring维护, 引入Spring管理的实例, 指定发布的接口, 注意version要和调用方保持一致
```
    <!--暴露出去的服务, 这里的版本和调用方要一致-->
    <dubbo:service interface="org.ko.api.UserService" ref="userServiceImpl" version="1.0.0"/>
```
- 实现API中接口, 交给Spring管理
```
    //接口的实现
    @Service    //由Spring管理
    public class UserServiceImpl implements UserService {
    
        @Override
        public String getUserName() {
            return "K.O";
        }
    }

```

**3.配置服务消费者**

- pom配置
```
    <groupId>org.ko</groupId>
    <artifactId>api</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
```

- yml文件中确定端口好
```
    server:
      port: 8081  #指定端口号为8081 区分端口
```
- 使用xml启动dubbo, 在resources/dubbo/config.xml中, 引入统一管理的消费接口配置文件consumer.xml
```
    <!--Dubbo服务名称 消费者-->
    <dubbo:application name="dubbo-consumer"/>
    
    <!-- dubbo指定注册中心zookeeper和地址-->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>

    <!--引入消费者-消费接口-->
    <import resource="consumer.xml"/>
```
- 配置消费调用接口, 注意version要和调用方保持一致, 这里的地址是接口的全名称
```
    <!--服务的超时时间 30秒-->
    <dubbo:consumer timeout="30000"/>

    <!--引用外部接口, interface本地接口, url服务方的地址-接口全名称, 调用服务的版本version要保持一致-->
    <dubbo:reference
            id="userService"
            interface="org.ko.api.UserService"
            version="1.0.0"/>
```
- 直接使用API模块中的接口进行调用
- SpringBoot 启动类中导入配置使用@ImportResource
```
    //SpringBoot启动
    @SpringBootApplication
    //引入Dubbo配置文件
    @ImportResource("dubbo/config.xml")
    public class ConsumerApplication {
    
        private static final Logger _LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);
    
        public static void main( String[] args ) {
    
            ConfigurableApplicationContext run = SpringApplication.run(ConsumerApplication.class, args);
            UserService userService = run.getBean(UserService.class);
            _LOGGER.info("UserService.getUser Return: {}", userService.getUser().toString());
        }
    }
```

**4.启动**

- 启动Zookeeper bin目录zkServer脚本
- 启动Provider.ProviderApplication
- 启动Consumer.ConsumerApplication
- Consumer 控制台输出
```
    UserService.getUser Return: UserDTO{id=1, name='K.O', age=27}
```
- 配置完成

**5.Zookeeper价值**

- 负载均衡：单注册中心的承载能力是有限的, 在流量达到一定程度的时候就需要分流, 负载均衡就是为了分流而存在的, 一个ZooKeeper群配合相应的Web应用就可以很容易达到负载均衡
- 资源同步：单单有负载均衡还不够, 节点之间的数据和资源需要同步, ZooKeeper集群就天然具备有这样的功能
- 命名服务：将树状结构用于维护全局的服务地址列表, 服务提供者在启动的时候, 向ZK上的指定节点/dubbo/${serviceName}/providers目录下写入自己的URL地址, 这个操作就完成了服务的发布
- Mast选举
- 分布式锁

**6.结束**