# 注解配置SpringMVC

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

**5.启动**

- 在webapp下创建index.jsp
```
    <html>
        <body>
            <a href="/hello">Say Hello!</a>
        </body>
    </html>
```

**6.结束**

- 点击Say Hello!--发起请求/hello-->HelloController.hello()--返回-->hello-->ViewResolver-->WEB-INF/views/hello.jsp