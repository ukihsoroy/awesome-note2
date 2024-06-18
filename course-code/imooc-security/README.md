# security

**1. 过滤器链**
1. 检查请求是否有请求信息-主要校验规则
    - `UsernamePasswordAuthenticationFilter`: 处理表单登录, 请求是否带用户名密码
    - `BasicAuthenticationFilter`: 是否有Basic码信息, 有会进行解码认证
    
2. `FilterSecurityInterceptor`: 最后一部, 决定当前请求能不能继续, 根据不过的原因会抛出异常.
3. `ExceptionTranslationFilter`: 在前`FilterSecurityInterceptor`有一个`ExceptionTranslationFilter`会处理`FilterSecurityInterceptor`抛出的异常, 引到用户根据前面过滤器的信息处理异常.
4. security通过过滤器链判断用户是否授权, 读取HttpSecurity配置, 判断请求中是否携带信息, 选择需要执行的过滤器, 最后由`ExceptionTranslationFilter`统一进行权限认证, 如果未通过, 则会抛出异常, 由`ExceptionTranslationFilter`统一进行异常处理, 最后将异常信息返回给前台.
![security调用链](/static/security-filter-process.png)

**2. 认证流程**
```flow
st=>start: 登陆请求
op1=> operation: UsernamePasswordAuthenticationFilter(Authentication未认证)
op2=> operation: AuthenticationManager
op3=> operation: AuthenticationProvider
op4=> operation: UserDetailsService
op5=> operation: UserDetails
op6=> operation: Authentication已认证
op7=> operation: SecurityContext
op8=> operation: SecurityContextHolder
op9=> operation: SecurityContextPersisitenceFilter
e=>end: End
    
```
1. 认证流程处理说明
    - `UsernamePasswordAuthenticationFilter`: 登陆请求未认证状态进入, 设置账户密码, 还未获取权限, 设置一些用户信息IP SESSION
    - `AuthenticationManager`: 负责收集所有`AuthenticationProvider`, 循环所有`AuthenticationProvider`获取是否支持, 挑出一个provider来获取用户认证
    - `AuthenticationProvider`: 真正校验的逻辑
    - `UserDetailsService`: 获取`UserDetails`获取权限信息返回给`UsernamePasswordAuthenticationFilter`, 成功`SuccessHandler`, 失败`FailureHandler`
    - `UserDetails`: `Authentication`已认证
    
2. 认证结果如何在多个请求间共享
    - `SecurityContext`: 在认证成功之前方式上下文 
    - `SecurityContextHolder`: ThreadLocal(线程级环境变量)封装, 获取认证信息
    - `SecurityContextPersisitenceFilter`: 在整个过滤器链的最前面, request进来检查Session中是否有SecurityContext如果有放到`SecurityContextHolder`没有空过, response回来检查`SecurityContextHolder`是否有`SecurityContext`, 如果有放置到Session中, 没有空过;  

3. 获取认证用户信息
    - SecurityContextHolder.getContext().getAuthentication(): 静态方法获取
    - Controller接口方法参数: Authentication authentication Spring mvc会自动注入
    - @AuthenticationPrincipal UserDetails userDetails： 使用朱姐@AuthenticationPrincipal UserDetails 只注入用户信息
    

**3.记住我功能**

    - 浏览器发起认证请求, 当认证成功会调用`RemeberMeService`, 在`RemeberMeService`中有`TokenRepository`, `RemeberMeService`会生成一个Token并写到Cookie中, 而`TokenRepository`会把Token存到数据库中, Token会一一对应;
    - 当用户第二天在登录, RemeberMeAuthenticationFilter会读取Cookie中的Token, 交给`RemeberMeService`在由`TokenRepository`去数据库中看有没有用户名, 有的话会把用户名交给UserDetailsService, 由其完成权限动作;
    - RemeberMeService.onLoginSuccess()--->生成Token 入库, 写cookie
    
**4.添加验证码**

**5.手机验证码登录**

**6.OAuth协议**

    - OAuth协议要解决的问题
        - 可以获取所有权限
        - 只能修改密码才能收回授权
        - 密码泄漏可能性大大提高
    - OAuth中的各种角色
        - 服务提供商(Provider): 提供令牌
            - 认证服务器(Authoriaztion Server): 生成Token
            - 资源服务器(Resource Server): 存放资源, 验证令牌
        - 资源所有者(Resource Owner): 用户
        - 第三方应用(Client): 
    - OAuth协议运行流程
        0. 用户访问第三方应用
        1. 请求用户授权
        2. 同意授权
            - 授权码模式(authorization code)
            - 密码模式(resource owner password credentials)
            - 客户端模式(client credentials)
            - 简化模式(implicit)
        3. 申请令牌
        4. 发放令牌
        5. 申请资源
        6. 开放资源
        
    - 授权码模式实现步骤(特点: 1.用户同意授权是在认证服务器上完成的, 2.接受到的是授权码(要求第三方应用必须有服务器))
        1. 将用户导向认证服务器
        2. 用户统一授权
        3. 返回Client并携带授权码
        4. 用授权码申请令牌(客户端后台服务器)
        5. 认证服务器检查授权码, 发放令牌
        
**7. Spring Social基本原理**

    1. ServiceProvider: 服务提供商接口
        - AbstractOAuth2ServiceProvider: Spring实现了一些通用的逻辑
        - OAuth2Operations(OAuth2Template): OAuth协议相关操作
        - API(AbstractOAuth2ApiBinding): 自己实现获取用户信息的接口
        - Connection(OAuth2Connection): 封装前面获取的用户信息
        - ConnectionFactory(OAuth2ConnectionFactory): 创建连接对象工厂. 包含ServiceProvider, ApiAdapter(做不同用户信息适配)
        - UsersConnectionRepository(JdbcUsersConnectionRepository): 将用户信息和连接保存在数据库UserConnection表