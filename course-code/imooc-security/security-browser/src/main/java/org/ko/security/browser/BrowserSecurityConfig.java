package org.ko.security.browser;

import org.ko.security.browser.authentication.AuthenticationFailureHandlerImpl;
import org.ko.security.browser.authentication.AuthenticationSuccessHandlerImpl;
import org.ko.security.browser.session.ExpiredSessionStrategyImpl;
import org.ko.security.core.properties.SecurityProperties;
import org.ko.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired private SecurityProperties securityProperties;

    /**
     * 成功处理器
     */
    @Autowired private AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;

    /**
     * 失败处理器
     */
    @Autowired private AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;

    @Qualifier("dataSource")
    @Autowired private DataSource dataSource;

    @Autowired private UserDetailsService userDetailsService;

    @Autowired private SpringSocialConfigurer springSocialConfigurer;

    /**
     * 记住我功能的实现,
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository () {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //初始化验证码过滤器
        ValidateCodeFilter filter = new ValidateCodeFilter();
        //设置失败处理器
        filter.setAuthenticationFailureHandler(authenticationFailureHandlerImpl);
        //设置全局配置
        filter.setSecurityProperties(securityProperties);
        //调取初始化方法
        filter.afterPropertiesSet();


//          http.httpBasic() //默认认证方式
        http
                .apply(springSocialConfigurer) //往过滤器链添加过滤器
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) //添加过滤器在账号密码验证之前
                .formLogin() //表单登录
                    .loginPage("/authentication/require")
                //            .loginPage("/ko-login.html") //返回登录页
                    .loginProcessingUrl("/authentication/form")//用usernamePasswordFilter来处理请求
                    .successHandler(authenticationSuccessHandlerImpl)
                    .failureHandler(authenticationFailureHandlerImpl)
                //记住我功能配置
                .and()
                    .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1) //同时存在最大session数为1
//                .maxSessionsPreventsLogin(true) 当session达到最大数量后 阻止后面用户登录
                .expiredSessionStrategy(new ExpiredSessionStrategyImpl()) //实现谁踢掉后记录, 有个事件
                .and()
                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/ko-logout.html")
                .and()
                .authorizeRequests()//下面的请求
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getSingUpUrl(),
                        securityProperties.getBrowser().getLoginPage(),
                "/user/register",
                "/session/invalid",
                "/code/*").permitAll()//放过这个URL-直接放行
                .anyRequest()   //所有的请求
                .authenticated() //都需要认证
                .and()
                .csrf().disable();
    }
}
