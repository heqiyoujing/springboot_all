package com.example.mapper.mybatisMap.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: yiqq
 * @date: 2018/8/8 20:37
 * @description:
 */
@Configuration
public class InterceptorConfigurerAdapter extends WebMvcConfigurerAdapter {


    //把我们的拦截器注入为bean
    @Bean
    public HandlerInterceptor getMyInterceptor(){
        return new MyInterceptor();
    }

    /**
     * 该方法用于注册拦截器
     * 可注册多个拦截器，多个拦截器组成一个拦截器链
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 添加路径
        // excludePathPatterns 排除路径
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
