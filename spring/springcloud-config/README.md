# Spring Cloud Config 配置中心

**1.父pom依赖, 使用的Boot-1.59 Cloud-Edgware**
```
    <!--Spring Boot 父依赖-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
        <relativePath/>
    </parent>
      
      
    <dependencies>
      <!--Spring Boot 测试模块-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
      </dependency>
  
      <!--Spring Boot 项目管理-->
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
      </dependency>
    </dependencies>
    
    <!--Spring Cloud 引用 各模块的版本等-->
    <dependencyManagement>
        <dependencies>
          <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Edgware.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
          </dependency>
        </dependencies>
    </dependencyManagement>
```

**2.配置注册中心：eureka-server**

- pom依赖
```
    <dependencies>
        <!--Eureka Server 注册中心依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
    </dependencies>
```

- application.yml配置

```
    server:
      port: 8081  #注册中心端口
    
    spring:
      application:
        name: eureka-server #服务名称
    
    eureka:
      instance:
        hostname: localhost #实例地址
      client:
        serviceUrl:
          defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/  #访问地址
        register-with-eureka: false #不注册自己
        fetch-registry: false #注册中心职责就是维护实例, 不需要检索服务
```

- EurekaServerApplication：boot启动类

```
    //Spring Boot 应用-启动入口
    @SpringBootApplication
    //自动启动Eureka注册中心
    @EnableEurekaServer
    public class EurekaServerApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(EurekaServerApplication.class, args);
        }
    }
```

**3.配置中心：config-server**

- pom依赖-引入服务发布相关依赖

```
    <dependencies>
        <!--Spring Cloud Config Server 依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>

        <!--开启Config向Eureka注册中心发布服务-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
    </dependencies>
```

- application.yml配置

```
    server:
      port: 8082 #端口号
    
    spring:
      application:
        name: config-server #Spring应用名称-SOA服务名称
      profiles:
          active: native  #使用本地文件系统-如使用svn, git去掉此配置
      cloud:
        config:
          server:
            native:   #使用本地文件系统可以指定文件目录
              searchLocations: file:D:\tmp\conf-repo  #config搜索配置文件目录
    
    #        使用svn需要引入pom依赖
    #        svn:     #SVN配置
    #          uri:           #SVN仓库位置
    #          search-paths:  #配置仓库路径下相对搜索位置,可配置多个
    #          username:      #SVN仓库用户名
    #          password:      #SVN仓库密码
    
    #        git:     #GIT配置
    #          url:           #GIT仓库位置
    #          search-paths:  #配置仓库路径下相对搜索位置,可配置多个
    #          username:      #GIT仓库用户名
    #          password:      #GIT仓库密码
    
    # 注册中心地址
    eureka.client:
      serviceUrl.defaultZone: http://localhost:8081/eureka/
```
```
    config-server, 支持svn, git, local管理配置文件
    git: 官方推荐使用
    svn: 需要引入svn pom依赖
    local: 1)需要指定spring.profiles.active=native
           2)注意searchLocations前缀不指定为spring默认classpath, 如访问本地请使用file
```

- 在D盘/tmp/conf-repo/目录下创建user-dev.yml文件, 在工程根目录下有demo
```
    # 远程读取属性
    name: K.O
    age: 28
```

- ConfigServerApplication: boot启动类

```
    //SpringBoot应用
    @SpringBootApplication
    //开启SpringCloud Config配置服务器
    @EnableConfigServer
    //开启Eureka连接
    @EnableDiscoveryClient
    public class ConfigServerApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(ConfigServerApplication.class, args);
        }
    }
```

**5.compute-service模块**

- pom依赖
```
    <dependencies>
        <!--Eureka服务发布组件 web服务 ribbon负载均衡等-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>

        <!--Spring Cloud Config 配置中心连接-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-client</artifactId>
        </dependency>
    </dependencies>
```

- Spring Cloud Config启动配置文件bootstrap.yml
```
    # 端口号
    server:
      port: 8083
    
    spring:
      application:
        name: user    #定义服务名
      profiles:
          active: dev #生效的配置文件
      cloud:
        config:
          discovery:
           enabled: true  #开启通过服务ID访问配置
           serviceId: config-server #配置中心服务ID
    
    # 定义注册中心地址
    eureka:
      client:
        serviceUrl:
          defaultZone: http://localhost:8081/eureka/
    
    #关闭Actuator security权限验证
    management.security.enabled: false
```
```
    工程启动后, 会访问config-server读取配置文件规则${spring.application.name}-${spring.profiles.active}.yml
```


- 创建Controller, 注意使用@RefreshScope开启刷新配置文件功能

```
    //开启配置刷新
    @RefreshScope
    //Rest
    @RestController
    //全局映射
    @RequestMapping("services")
    public class ServicesController {
    
        //获取服务相关信息
        @Autowired private DiscoveryClient client;
    
        //读取配置文件name
        @Value("${name}") private String name;
    
        //读取配置文件age
        @Value("${age}") private Short age;
    
        @GetMapping
        public String getServices() {
            String desc = client.description();
            return desc;
        }
    
        @GetMapping("data")
        public String getData () {
            return String.format("name: %s, age: %d", name, age);
        }
    
    }
```

- ComputeServiceApplication: 启动类
```
    //Spring Boot 程序
    @SpringBootApplication
    //向Eureka注册中心发布服务
    @EnableEurekaClient
    public class ComputeServiceApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(ComputeServiceApplication.class, args);
        }
    }
```



**6.启动**

- 启动EurekaServerApplication-注册中心
- 启动ConfigServerApplication-发布服务
```
    GET访问：http://localhost:8082/user/dev查看配置信息
    URL映射规则：/{application}/{profiles}-->{application}-{profiles}.yml
    注意: 
        1)demo中并没有使用lable, 如添加请访问http://localhost:8082/user/dev/{lable}
        2)lable对应文件目录/{lable}/{application}-{profiles}.yml
```
- 启动ComputeServiceApplication-服务消费

**7.测试**

- /json目录PostMan Collection请求脚本-导入后测试
```
    POST: http://localhost:8083/refresh 刷新配置
```

**8.结束**