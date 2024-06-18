# Spring WebMVC Interceptor拦截器简单应用

**1.pom引入SpringMVC jar依赖**
```
    <dependencies>
        <!-- SpringMVC依赖-包括Spring核心依赖 -->
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-webmvc</artifactId>
          <version>4.3.13.RELEASE</version>
        </dependency>
    </dependencies>
```

**2.WEB-INF/web.xml配置**
```
    <!-- 配置DispatchcerServlet -->
    <servlet>
        <!--指定Servlet-->
        <servlet-name>springDispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        
        <!-- 配置Spring MVC下的配置文件的位置和名称 -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <!--表示拦截请求格式 /表示全部拦截-->
    <servlet-mapping>
        <servlet-name>springDispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```
```
    注意：
        1)<init-param></init-param>配置SpringMVC的xml配置, 需要在resources目录下创建. 
        2)使用默认Servlet读取规则[servlet-name]-servlet.xml, 对应springDispatcherServlet-servlet.xml
        3)param-value中classpath:Spring项目根目录默认前缀, 这里Spring默认读取resources目录下配置
```

**3.配置SpringMVC配置文件**

```
    <!-- 配置自动扫描的包 -->
    <context:component-scan base-package="org.ko.mvc"/>

    <!-- 配置视图解析器, 渲染页面-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <!--为返回的视图名称添加前缀-->
        <property name="prefix" value="/WEB-INF/views/"></property>
        <!--为返回的视图名称添加后缀-->
        <property name="suffix" value=".jsp"></property>
    </bean>
```

**4.编写Controller**

```
    /**
     * 标记当前类为控制层
     * Spring扫描此注解
     * 将Mapping对应映射关系保存到容器
     */
    @Controller
    public class HelloController {
    
        //配置URL映射
        @RequestMapping("/hello")
        public String hello () {
            //返回视图名称
            return "hello";
        }
    }
```

**5.实现HandlerInterceptor**

- 实现访问/{code}时, 当请求数字大于零时, 返回Hello, World!. 否则直接返回{code}
```
    /**
     * SpringMVC 拦截器
     * 三个阶段
     * 1) 请求到Controller前, #{@link #preHandle}
     * 2) Controller执行后, DispatcherServlet视图渲染前, #{@link #postHandle}
     * 3) 视图渲染后, #{@link #afterCompletion}
     * 4) 2,3都依赖1结果为true
     */
    public class GlobalInterceptor implements HandlerInterceptor {
    
        private static final Logger _LOGGER = LoggerFactory.getLogger(GlobalInterceptor.class);
        /**
         * 请求到Controller前执行
         * 返回false则请求结束, 不会进入Controller
         */
        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response, Object handler) throws Exception {
            String url = request.getRequestURI();
            _LOGGER.info("Request url: {}", url);
            if (!StringUtils.isEmpty(url)) {
                try {
                    url = url.replaceAll("/", "");
                    if (Integer.valueOf(url) > 0) {
                        request.setAttribute("code", "Hello, World!");
                    }
                } catch (NumberFormatException nfe) {
                    _LOGGER.info("Number format error: {} not number.", url);
                }
            }
            return true;
        }
    
    
        /**
         * Controller执行后, DispatcherServlet视图渲染前执行
         * 用来处理一些Model数据等
         */
        @Override
        public void postHandle(HttpServletRequest request,
                               HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    
        }
    
        /**
         * 视图渲染完成后执行
         * 用来进行资源清理等
         */
        @Override
        public void afterCompletion(HttpServletRequest request,
                                    HttpServletResponse response, Object handler, Exception ex) throws Exception {
    
        }
    }
```
- 方法解释：
```
    1) preHandle(): 请求在进入Controller前会先进入这个方法, 如果该方法返回false请求返回, 可以在这里做一些验证, 或者修改一些参数等等.
    2) postHandle(): 进入该方法时Controller已经执行完毕, 但页面还未渲染, 这里可以做一些视图渲染或者Model方面的改动.
    3) afterCompletion(): 此时页面已经渲染完毕, 该方法可以做一些资源清理等操作.
```

**6.配置Interceptor**

- 在spring-mvc.xml中添加配置
```
    <!--自定义拦截器配置-->
    <mvc:interceptors>
        <mvc:interceptor>
            <!--拦截的请求路径-->
            <mvc:mapping path="/**"/>
            <!--执行的拦截器-->
            <bean class="org.ko.mvc.interceptor.GlobalInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
```


**7.启动**

- 在webapp下创建index.jsp
```
    <html>
        <body>
            <a href="/hello">Click Hello!</a>
            <br/>
            <a href="/1">Click Number!</a>
            <br/>
            <a href="/Artist">Click Artist</a>
        </body>
    </html>
```

**8.结束**
