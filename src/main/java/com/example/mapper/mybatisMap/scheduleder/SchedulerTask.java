package com.example.mapper.mybatisMap.scheduleder;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SchedulerTask {
    @Scheduled(cron="0/30 * *  * * ? ")
    public void aTask(){
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(sdf.format(new Date())+"*********A任务每30秒执行一次进入测试");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
