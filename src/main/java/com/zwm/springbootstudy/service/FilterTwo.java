package com.zwm.springbootstudy.service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

public class FilterTwo implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入第二个过滤器");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("销毁过滤器");
    }
}
