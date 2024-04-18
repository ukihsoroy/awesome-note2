package org.ko.security.config;

import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@EnableWebSecurity
public class T2WebSecurityConfig //extends WebSecurityConfigurerAdapter
{

    /**
     * 1.更新的配置指定登录页面的位置
     * 2.我们必须授予所有用户（即未经身份验证的用户）访问我们的登录页面。该formLogin().permitAll()方法允许所有用户访问与基于表单的登录相关的所有URL。
     * @param http
     * @throws Exception
     */
//    protected void configure (HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                        .anyRequest().authenticated()
//                        .and()
//                .formLogin()
//                        .loginPage("/login")  //1
//                        .permitAll(); //2
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService () {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
//        return manager;
//    }


}
