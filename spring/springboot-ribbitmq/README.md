# Spring Boot 使用RabbitMQ

### 1.Windows RabbitMQ安装

- 官网下载：[RabbitMQ-3.7.2.exe](https://dl.bintray.com/rabbitmq/all/rabbitmq-server/3.7.2/rabbitmq-server-3.7.2.exe)
- 安装完毕进入/sbin目录, 启动CMD
- 开启管理界面
```
    <!--1.查看已安装插件-->
    rabbitmq-plugins.bat list
    <!--2.开启管理界面-->
    rabbitmq-plugins.bat enable rabbitmq_management
```
- 创建用户, 赋予权限
```
    <!--1.创建用户->
    rabbitmqctl.bat  add_user  tiger  123456
    <!--2.赋予角色-->
    rabbitmqctl.bat  set_user_tags  tiger  administrator
    <!--3.设置权限-->
    rabbitmqctl.bat  set_permissions -p / tiger .* .* .*
```
- 访问管理界面: [http://127.0.0.1:15672/](http://127.0.0.1:15672/)
- 创建队列`ko-queue`，添加name其他默认即可。

### 2.配置工程

**1.pom配置, 在Spring Boot工程中引入amqp依赖, RabbitMQ为默认实现**
```
    <!--Spring Boot AMQP协议快速启动 包括Boot Starter, Massaging, RabbitMQ-->
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
```

**2.配置生产者**

- application.yml配置, 启动端口和RabbitMQ一些初始化配置

```
    server:
      port: 8081  #应用端口号
    
    spring:
      application:
        name: amqp-provider #应用名称
      rabbitmq:
        host: localhost #RabbitMQ 地址
        port: 5672  #监听端口号
        username: tiger #用户名
        password: 123456  #密码
```

- 创建Send服务类
```
    /**
     * 发送消息到RabbitMQ
     * 维护Queue名称
     */
    @Component
    public class AmqpSenderService {
    
        private static final Logger _LOGGER = LoggerFactory.getLogger(AmqpSenderService.class);
    
        @Autowired AmqpTemplate amqpTemplate;
    
        /**
         * 发送消息
         * @param message
         */
        public void send (String message) {
            _LOGGER.info("Send message: {}", message);
            amqpTemplate.convertAndSend("ko-queue", message);
        }
    }
```

- 单元测试发送消息
```
    @SpringBootTest
    @RunWith(SpringRunner.class)
    public class AmqpTests {
    
        @Autowired
        AmqpSenderService amqpSenderService;
    
        @Test
        public void sendTest () {
            for (int i = 1; i < 100; i++) {
                amqpSenderService.send("message" + i);
            }
        }
    
    }
```

**3.配置消费者**

- application.yml配置, 修改端口号, 与provider区别
```
    server:
      port: 8082  #应用端口号
    
    spring:
      application:
        name: amqp-provider #应用名称
      rabbitmq:
        host: localhost #RabbitMQ 地址
        port: 5672  #监听端口号
        username: tiger #用户名
        password: 123456  #密码
```

- 创建消息接收

```
    @Component
    //监听的消息队列
    @RabbitListener(queues = "ko-queue")
    public class RabbitReceiver {
    
        private static final Logger _Logger = LoggerFactory.getLogger(RabbitReceiver.class);
    
        @RabbitHandler
        public void process (String message) {
            _Logger.info("Receiver message: {}", message);
        }
    }
```

### 3.启动

- 启动RabbitMQ: /sbin/rabbitmq-server.bat
- 启动消费者：AmqpConsumerApplication
- 启动生产者单元测试：AmqpTests.sendTest()

### 4.结束 