package com.example.mapper.mybatisMap.threadmodel;


import com.example.mapper.mybatisMap.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class ThreadModel implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(ThreadModel.class.getName());
    private String name;
    private User user;
    private CountDownLatch latch;

    private  ThreadModel(String name, User user, CountDownLatch latch) {
        this.name = name;
        this.user = user;
        this.latch = latch;
    }

    public static ThreadModel getInstance(
            String name,
            User user,
            CountDownLatch latch
    ) {
        return new ThreadModel(name, user, latch);
    }

    public void run(){
        Long time = System.currentTimeMillis();
        user.setId(1);
        user.setName(name);
        user.setRole("程序员");
        logger.info("ThreadModel",time.intValue(), user.getId());
        latch.countDown();
    }
}
