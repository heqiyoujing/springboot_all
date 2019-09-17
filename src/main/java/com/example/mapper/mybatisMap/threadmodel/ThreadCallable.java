package com.example.mapper.mybatisMap.threadmodel;


import com.example.mapper.mybatisMap.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadCallable {
    private String sessionId = "";
    private static Logger logger = LoggerFactory.getLogger(ThreadCallable.class.getName());
    User user;
    public ThreadCallable(User user, String sessionId){
        this.sessionId = sessionId;
        this.user = user;
    }

    public boolean getUser(){
        try{
            List<Future> list = new ArrayList<Future>();
            ExecutorService executorService = Executors.newFixedThreadPool( 10 );

            Callable clubInfoThread = UserInfoThread.getInstance(sessionId, user);
            Future userSubmit = executorService.submit(clubInfoThread); //execute没有返回值，而submit有返回值。
            list.add(userSubmit);

            Callable clubInfoThread1 = UserInfoThread.getInstance(sessionId, user);
            Future userSubmit1 = executorService.submit(clubInfoThread1);
            list.add(userSubmit1);

            Callable clubInfoThread2 = UserInfoThread.getInstance(sessionId, user);
            Future userSubmit2 = executorService.submit(clubInfoThread2);
            list.add(userSubmit2);

            for (Future future : list) {
                future.get();
            }
            // 关闭线程池
            executorService.shutdown();

        } catch (Exception e) {
            logger.info("errormsg", e.getMessage());
        }finally {
            return true;
        }
    }
}
