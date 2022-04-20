package com.zwm.springbootstudy.handler;

import com.zwm.springbootstudy.service.ServletTwo;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {
    @Bean
    public ServletRegistrationBean myServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ServletTwo(), "/servlet/two");
        return servletRegistrationBean;
    }
}