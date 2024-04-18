package org.ko.security.config;

import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@EnableWebSecurity
public class T1WebSecurityConfig //extends WebSecurityConfigurerAdapter
{

    /**
     * 确保对我们应用程序的任何请求都要求用户进行身份验证
     * 允许用户使用基于表单的登录进行身份验证
     * 允许用户使用HTTP基本认证进行认证
     * @param http
     * @throws Exception
     */
//    protected void configure (HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                        .anyRequest().authenticated()
//                        .and()
//                .formLogin()
//                        .and()
//                .httpBasic();
//    }

//    @Bean
//    public UserDetailsService userDetailsService () {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
//        return manager;
//    }
}
