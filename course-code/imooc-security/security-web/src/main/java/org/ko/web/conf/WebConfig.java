package org.ko.web.conf;

import org.ko.web.filter.TimeFilter;
import org.ko.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired private TimeInterceptor timeInterceptor;

    /**
     * 注册过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean timeFilter () {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(timeInterceptor);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
        //注册callable拦截器
//        configurer.registerCallableInterceptors();
        //注册deferredResult拦截器
//        configurer.registerDeferredResultInterceptors()
        //异步请求超时时间
//        configurer.setDefaultTimeout()
        //设定异步请求线程池callable等, spring默认线程不可重用
//        configurer.setTaskExecutor()
    }
}
