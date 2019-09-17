package com.example.mapper.mybatisMap.controller;

import com.example.mapper.mybatisMap.SpringBootAll;
import com.example.mapper.mybatisMap.entity.User;
import com.example.mapper.mybatisMap.library.JobPool;
import com.example.mapper.mybatisMap.library.LimitQueue;
import com.example.mapper.mybatisMap.threadmodel.ThreadModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import java.util.concurrent.CountDownLatch;

/**
 * @author: yiqq
 * @date: 2018/7/20
 * @description:
 */
@RestController
public class ThreadController {
    private static Logger logger = LoggerFactory.getLogger(ThreadController.class);

    /**
     * 多线程调用model
     */
    @RequestMapping(value = "/thread", method = RequestMethod.GET)
    @ResponseBody
    public String Get(@RequestParam(value = "id",defaultValue = "1") String id) throws Exception{
        CountDownLatch latch = new CountDownLatch(1);
        User user = new User();
        String msg = "";
        new JobPool().getPool().execute(
                ThreadModel.getInstance("yiqq", user, latch)
        );
        latch.await();
        if( !StringUtils.isEmpty(user.getName())){
            msg = user.getName();
        }

        LimitQueue<Long> lq = SpringBootAll.getLimitQueue();

        return msg;
    }
}
