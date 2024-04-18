# Spring Boot 使用WebSocket搭建简单聊天室

**1.创建项目引入SpringBoot依赖**

**2.pom中添加WebSocket依赖, 引入spring-boot-starter-websocket**
```
    <dependencies>
        <!--WebSocket快速启动, 包含Web, messaging, WebSocket-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-websocket</artifactId>
        </dependency>

        <!--Boot 测试组件-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>

        <!--使用Thymeleaf模版-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
    </dependencies>
```
```
    1) 注意引入spring-boot-starter-parent父pom
    2) spring-boot-starter-websocket包含了boot-starter和web不需要重复引入
    3) 本次使用的Thymeleaf模板引擎, 需要引入对应组件
```

**3.为Spring Boot容器注入ServerEndpointExporter**
```
    @Configuration
    public class WebSocketConfig {
    
        /**
         * 配置WebSocketEndpointServer
         * 如果使用独立的servlet容器，不是使用SpringBoot的内置容器
         * 不需要注入ServerEndpointExporter, 它将由容器自己提供和管理
         * @return
         */
        @Bean
        public ServerEndpointExporter serverEndpointExporter () {
            return new ServerEndpointExporter();
        }
    }
```

**4.使用@ServerEndpoint("/endpoint")维护消息端点**

```
    //开启Spring管理
    @Component
    //表示发送消息端点
    @ServerEndpoint("/endpoint")
    public class SimpleEndpoint {
    
        /**
         * 日志
         */
        private static final Logger _Logger = LoggerFactory.getLogger(SimpleEndpoint.class);
    
        /**
         * 线程安全基本数据
         */
        private static AtomicInteger COUNT = new AtomicInteger();
    
        /**
         * 在线用户实例
         */
        private static CopyOnWriteArraySet<SimpleEndpoint> container = new CopyOnWriteArraySet<SimpleEndpoint>();
    
        /**
         * 当前用户Session
         */
        private Session session;
    
        //获取连接时调用
        @OnOpen
        public void onOpen (Session session) {
            this.session = session;
            container.add(this);
            addUser();
        }
    
        //连接关闭时调用
        @OnClose
        public void onClose () {
            //从容器中删除用户
            container.remove(this);
            //减去用户数量
            subUser();
        }
    
        //接受WebSocket发送的消息
        @OnMessage
        public void onMessage (String message, Session session) {
            _Logger.info("Send all user: {}", message);
            container.forEach(target -> target.sendMessage(message));
        }
    
        //发生错误时调用
        @OnError
        public void onError (Session session, Throwable error) {
            _Logger.info("error: {}", error.getMessage());
        }
        
        /**
         * 发送消息
         * @param message
         */
        public void sendMessage (String message) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        /**
         * 自定义发送消息给全部用户
         * @param message
         */
        public static void sendUsers (String message) {
            _Logger.info("Send all user: {}", message);
            container.forEach(target -> target.sendMessage(message));
        }
    
        /**
         * 添加用户数量
         */
        public void addUser () {
            _Logger.info("Add user: {}", COUNT.get());
            SimpleEndpoint.COUNT.addAndGet(1);
        }
    
        /**
         * 减去用户数量
         */
        public void subUser () {
            _Logger.info("Sub user: {}", COUNT.get());
            SimpleEndpoint.COUNT.addAndGet(-1);
        }
    }
```
- 注解讲解
```
    1) @ServerEndpoint(endpoint): 对应消息发送端点, 使用wx协议发送消息, url:wx://${ip}:${port}/${endpoint}
    2) @OnOpen: 开启监听, 获取连接后回调
    3) @OnClose: 开启关闭监听, 关闭连接后回调
    4) @OnMessage: 客户端发送消息监听, 发送消息后回调
    5) @OnError: 异常监听, 发送错误时调用
```

**5.前台创建WebSocket监听**

```
    <body onkeydown="enter()">
        <h2>简易聊天室!</h2>
        <div id="_window"></div>
        <input type="text" id="_text"/>
        <input type="button" id="_send" onclick="send()" value="发送"/>
        <script type="text/javascript">
            var socket = null;
            //判断当前浏览器是否支持WebSocket
            if ('WebSocket' in window) {
                socket = new WebSocket("ws://10.100.4.11:8081/endpoint");
            } else {
                alert('Not support socket')
            }
    
            //连接发生错误的回调方法
            socket.onerror = function (e) {
                setMessageInnerHTML("error");
            };
    
            //连接成功建立的回调方法
            socket.onopen = function () {
                setMessageInnerHTML("欢迎来到简易聊天室！");
            }
    
            //接收到消息的回调方法
            socket.onmessage = function (event) {
                setMessageInnerHTML(event.data);
            }
    
            //连接关闭的回调方法
            socket.onclose = function () {
                setMessageInnerHTML("close");
            }
    
            //监听窗口关闭事件，当窗口关闭时，主动去关闭socket连接，防止连接还没断开就关闭窗口，server端会抛异常。
            window.onbeforeunload = function () {
                socket.close();
            }
    
            //将消息显示在网页上
            function setMessageInnerHTML (innerHTML) {
                var span = document.createElement('span');
                span.className = '_message';
                span.innerHTML = innerHTML;
                var content = document.getElementById("_window");
                content.appendChild(span);
                content.appendChild(document.createElement('br'));
            }
    
            //关闭连接
            function closeWebSocket () {
                socket.close();
            }
    
            //发送消息
            function send () {
                var target = document.getElementById('_text');
                var message = target.value;
                socket.send(message);
                target.value = '';
            }
    
            function enter() {
                if (event.keyCode == "13") {
                    send();
                }
            }
        </script>
    </body>
```
- 注意修改socket监听IP, new WebSocket("ws://10.100.4.11:8081/endpoint");

**6.启动**

- 启动Application
- 访问[http://localhost:8081](http://localhost:8081)

**7.结束**

