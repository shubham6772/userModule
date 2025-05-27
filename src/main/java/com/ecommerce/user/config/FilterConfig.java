package com.ecommerce.user.config;

import com.ecommerce.user.filters.SessionFIlter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SessionFIlter> loggingFilter(){
        FilterRegistrationBean<SessionFIlter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SessionFIlter());
        registrationBean.addUrlPatterns("/*"); // Apply to all endpoints

        return registrationBean;
    }
}
