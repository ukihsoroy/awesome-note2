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
  </dependencies>
```

**2.配置服务提供方**

- yml文件中确定端口好
```
    server:
      port: 8080  #指定端口号为8080 区分端口
```
- 使用xml启动dubbo, 在resources/dubbo/config.xml中, 引入统一管理的提供接口
```
    <!--Dubbo的服务名称-->
    <dubbo:application name="dubbo-provider"/>
    
    <!--dubbo接口去除注册中心，采用直连的方式-->
    <dubbo:registry address="N/A"/>
    
    <!--引入统一维护的服务暴露接口-->
    <import resource="provider.xml"/>
```
- 配置对外暴露服务的接口, 实例是由Spring维护, 引入Spring管理的实例, 指定发布的接口, 注意version要和调用方保持一致
```
    <!-- 用dubbo协议在20880端口暴露服务 -->
    <dubbo:protocol name="dubbo" port="20880"/>
    
    <!--暴露出去的服务, 这里的版本和调用方要一致-->
    <dubbo:service interface="org.ko.rpc.api.UserService" ref="userServiceImpl" version="1.0.0"/>
```
- 创建接口, 实现接口, 交给Spring管理
```
    //对外发布的接口
    public interface UserService {
    
        String getUserName();
    }
    
    //接口的实现
    @Service    //由Spring管理
    public class UserServiceImpl implements UserService {
    
        @Override
        public String getUserName() {
            return "K.O";
        }
    }

```

**3.配置服务消费方**

- yml文件中确定端口好
```
    server:
      port: 8081  #指定端口号为8081 区分端口
```
- 使用xml启动dubbo, 在resources/dubbo/config.xml中, 引入统一管理的消费接口配置文件consumer.xml
```
    <!--Dubbo服务名称 消费者-->
    <dubbo:application name="dubbo-consumer"/>

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
            interface="org.ko.rpc.api.UserService"
            url="dubbo://127.0.0.1:20880/org.ko.rpc.api.UserService"
            version="1.0.0"/>
```
- 创建接口, 注意：Dubbo使用RPI来作为服务调用的标识, 生产中通常Provider和Consumer共同依赖同样一个API接口jar, 里面包含接口的DTO对象
```
    //调用的接口
    public interface UserService {
    
        String getUserName();
    }
```
- SpringBoot 启动类中导入配置使用@ImportResource
```
    //SpringBoot启动类
    @SpringBootApplication
    //引入Dubbo配置文件
    @ImportResource("dubbo/config.xml")
    public class ConsumerApplication {
    
        private static final Logger _LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);
    
        public static void main( String[] args ) {
    
            ConfigurableApplicationContext run = SpringApplication.run(ConsumerApplication.class, args);
            UserService userService = run.getBean(UserService.class);
            //查看是否成功获取
            _LOGGER.info("UserService.getUserName Return: {}", userService.getUserName());
        }
    }
```

**4.启动**
- 启动Provider.ProviderApplication
- 启动Consumer.ConsumerApplication
- Consumer 控制台输出
```
    UserService.getUserName Return: K.O
```
- 配置完成

**5.结束**
- 通常在环境中, 同一个应用即使消费方也是提供方, 注意好配置文件的区分方便维护.