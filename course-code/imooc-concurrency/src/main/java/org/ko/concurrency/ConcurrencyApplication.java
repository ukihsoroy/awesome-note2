package org.ko.concurrency;

import org.ko.concurrency.filter.HttpFilter;
import org.ko.concurrency.interceptor.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
public class ConcurrencyApplication extends WebMvcConfigurationSupport {
    public static void main(String[] args) {
        SpringApplication.run(ConcurrencyApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean httpFilter () {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new HttpFilter());
        registrationBean.addUrlPatterns("/threadLocal/*");
        return registrationBean;
    }

    @Autowired
    private HttpInterceptor httpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpInterceptor).addPathPatterns("/*");
        super.addInterceptors(registry);
    }
}
