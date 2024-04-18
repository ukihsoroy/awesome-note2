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
    
        <!--Dubbo快速启动 包含Dubbo, Zookeeper, ZK 版本1.0.0-->
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
        registry.address: N/A          #禁用注册
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
        registry.address: N/A          #禁用注册
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
         * url: 连接的url
         * version: 接口版本
         */
        @Reference(url = "dubbo://localhost:20880/org.ko.api.UserService", version = "1.0.0")
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

- 启动Provider.ProviderApplication
- 启动Consumer.ConsumerApplication
- Consumer 控制台输出
```
    ConsumerService.getUser Return: UserDTO{id=1, name='K.O', age=27}
```
- 配置完成
