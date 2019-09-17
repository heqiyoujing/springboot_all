package com.example.mapper.mybatisMap.threadmodel;

import com.example.mapper.mybatisMap.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class UserInfoThread implements Callable<Object> {
    public int workTime;
    CountDownLatch latch;
    private User user;
    private String sessionId;
    private static Logger logger = LoggerFactory.getLogger(ThreadCallable.class.getName());
    public UserInfoThread(String sessionId, User user){
        this.user = user;
        this.sessionId = sessionId;
    }

    public static UserInfoThread getInstance(String sessionId, User user){
        return new UserInfoThread( sessionId, user);
    }

    @Override
    public Object call(){
        try{
            Long startTime = System.currentTimeMillis();
            user.setId(175);
            user.setAge(27);
            user.setName("chengxuyuan");
        } catch (Exception e){
            logger.info("errormsg", e.getMessage());
        }finally {
            return user;
        }
    }

}
