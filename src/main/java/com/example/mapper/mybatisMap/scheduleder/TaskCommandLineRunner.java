package com.example.mapper.mybatisMap.scheduleder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1) //第一个顺序输出
public class TaskCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception{
        System.out.println("启动加载类------------");
    }
}
