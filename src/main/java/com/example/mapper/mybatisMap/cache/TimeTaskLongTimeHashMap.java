package com.example.mapper.mybatisMap.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;

/**
 * @author: yiqq
 * @date: 2018/7/20
 * @description:
 */
public class TimeTaskLongTimeHashMap extends TimerTask {

    private Logger logger = LoggerFactory.getLogger(TimeTaskLongTimeHashMap.class.getName());

    @Override
    public void run(){
        LongTimeHashMap longTimeHashMap = new LongTimeHashMap();
        Iterator iterator = longTimeHashMap.getMap().entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry)iterator.next();
            LongTime longTime = (LongTime) entry.getValue();
            if(longTime.getOverTime() < System.currentTimeMillis()){
                longTimeHashMap.getMap().remove(entry.getKey());
                logger.info("delete registerParamsKey:[%d] OverTime:[%s]",entry.getKey(),longTime);
            } else {
                continue;
            }
        }
    }
}
