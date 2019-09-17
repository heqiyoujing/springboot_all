package com.example.mapper.mybatisMap.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CacheTestServiceImpl implements CacheTestService{
    private final Map<String, String> enties = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(getClass());

    public CacheTestServiceImpl() {
        enties.put("1", "this no 1");
    }

    @Autowired
    private CacheManager cacheManager;

    @Cacheable(cacheNames = "test")
    public String get(String id) {
        // 记录数据产生的时间，用于测试对比
        long time = new Date().getTime();
        // 打印使用到的cacheManager
        logger.warn("The cacheManager is " + cacheManager);
        // 当数据不是从cache里面获取时，打印日志
        logger.warn("Get value by id=" + id + ", The time is " + time);
        return "Get value by id=" + id + ",the value is" + enties.get(id);
    }

}
