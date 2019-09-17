package com.example.mapper.mybatisMap.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author: yiqq
 * @date: 2018/8/8 20:32
 * @description:
 */
@WebFilter(filterName="ServletFilter",urlPatterns="/servlet/**")
public class ServletFilter implements Filter {
    /**
     * Default constructor.
     */
    public ServletFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        System.out.println("过滤器被销毁。");
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        System.out.println("过滤器正在执行...");
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("初始化过滤器。");
    }
}
