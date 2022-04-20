package com.zwm.springbootstudy;

import com.zwm.springbootstudy.service.FilterTwo;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean myFilterRegistrationBean() {
        String[] urlPatterns = {"/filter/two"};
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new FilterTwo());
        filterRegistrationBean.addUrlPatterns(urlPatterns);
        return filterRegistrationBean;
    }
}
