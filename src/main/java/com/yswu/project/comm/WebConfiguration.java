package com.yswu.project.comm;

import com.yswu.project.filter.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * session会话保存
 *
 * @Author yswu3
 * @Date 2019/11/1.
 */
@Configuration
public class WebConfiguration {
    public static final String LOGIN_KEY = "LOGIN_SESSION_KEY";
    public static final String LOGIN_USER = "LOGIN_SESSION_USER";

    @Bean(name = "sessionFilter")
    public Filter sessionFilter() {
        return new SessionFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("sessionFilter");
        registration.setOrder(1);
        return registration;
    }
}
