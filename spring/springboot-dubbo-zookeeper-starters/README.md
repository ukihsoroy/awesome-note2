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
    
        <!--Dubbo快速启动 包含Dubbo, Zookeeper, zkClient 版本1.0.0-->
        <dependency>
          <groupId>io.dubbo.springboot</groupId>
          <artifactId>spring-boot-starter-dubbo</artifactId>
          <version>${dubbo.starter}</version>
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

- yml文件中配置端口和Dubbo参数
```
    server:
      port: 8080  #指定端口号为8080 区分端口
      
    spring:
      dubbo:
        application.name: provider     #Dubbo服务名称
        registry.address: zookeeper://127.0.0.1:2181 #Zookeeper地址
        protocol:
          name: dubbo                  #使用Dubbo 自定义协议
          port: 20880                  #将Dubbo服务发布到20880端口
        scan: org.ko.rpc               #自动扫描Dubbo自定注解
```

- 实现API中接口, 交给Spring管理
```
    /**
     * 接口的实现
     * 提供服务的具体实现-注解为Dubbo自定注解
     * com.alibaba.dubbo.config.annotation.Service;
     */
    @Service(version = "1.0.0")
    public class UserServiceImpl implements UserService {
        @Override
        public UserDTO getUser() {
            UserDTO user = new UserDTO();
            user.setId(1L);
            user.setName("K.O");
            user.setAge((short)27);
            return user;
        }
    }

```
- SpringBoot 启动类
```
    //SpringBoot启动
    @SpringBootApplication
    public class ProviderApplication {
        public static void main( String[] args ) {
            SpringApplication.run(ProviderApplication.class, args);
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

- yml文件中配置端口和Dubbo参数
```
    server:
      port: 8081  #指定端口号为8081 区分端口
    spring:
      dubbo:
        application.name: consumer     #Dubbo服务名称
        registry.address: zookeeper://127.0.0.1:2181 #Zookeeper地址
        protocol:
          name: dubbo                  #使用Dubbo 自定义协议
          port: 20880                  #将Dubbo服务发布到20880端口
        scan: org.ko.rpc               #自动扫描Dubbo自定注解
```
- 创建调用的类-获取远程服务
- 使用Dubbo @Reference注解-填写参数, 获取远程服务
```
    //Spring 注解
    @Service
    public class ConsumerService {
        /**
         * version: 接口版本
         */
        @Reference(version = "1.0.0")
        private UserService userService;
    
        public UserDTO getUser() {
            return userService.getUser();
        }
    
    }
```
- SpringBoot 启动类检测是否成功
```
    //SpringBoot启动
    @SpringBootApplication
    public class ConsumerApplication {
    
        private static final Logger _LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);
    
        public static void main( String[] args ) {
    
            ConfigurableApplicationContext run = SpringApplication.run(ConsumerApplication.class, args);
            ConsumerService consumerService = run.getBean(ConsumerService.class);
            _LOGGER.info("ConsumerService.getUser Return: {}", consumerService.getUser().toString());
        }
    }
```

**4.启动**

- 启动Zookeeper bin目录zkServer脚本
- 启动Provider.ProviderApplication
- 启动Consumer.ConsumerApplication
- Consumer 控制台输出
```
    ConsumerService.getUser Return: UserDTO{id=1, name='K.O', age=27}
```
- 配置完成

**5.Zookeeper价值**

- 负载均衡：单注册中心的承载能力是有限的, 在流量达到一定程度的时候就需要分流, 负载均衡就是为了分流而存在的, 一个ZooKeeper群配合相应的Web应用就可以很容易达到负载均衡
- 资源同步：单单有负载均衡还不够, 节点之间的数据和资源需要同步, ZooKeeper集群就天然具备有这样的功能
- 命名服务：将树状结构用于维护全局的服务地址列表, 服务提供者在启动的时候, 向ZK上的指定节点/dubbo/${serviceName}/providers目录下写入自己的URL地址, 这个操作就完成了服务的发布
- Mast选举
- 分布式锁

**6.结束**