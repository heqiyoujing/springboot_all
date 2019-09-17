package com.example.mapper.mybatisMap.controller;

import com.example.mapper.mybatisMap.cache.LocalCache;
import com.example.mapper.mybatisMap.dao.CacheTestService;
import com.example.mapper.mybatisMap.dao.CacheTestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yiqq
 * @date: 2018/7/20
 * @description:
 */

@RestController
public class CacheController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * guava cache设置缓存
     */
    @RequestMapping(value = "/setcache", method = RequestMethod.GET)
    @ResponseBody
    public String setCache() {
        LocalCache localCache = new LocalCache();
        localCache.setCache("yiqq", 12);
        return "success";
    }

    /**
     * guava cache获取缓存
     */
    @RequestMapping(value = "/getcache", method = RequestMethod.GET)
    @ResponseBody
    public String getCache() {
        String ss = "";
        LocalCache localCache = new LocalCache();
        if( localCache.getCache("yiqq") != null) {
            ss = "success";
        }else{
            ss = "error";
        }
        return ss;
    }

    /**
     * CacheManager缓存(注意启动入口需要添加@EnableCaching注解)
     */
    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    @ResponseBody
    public String Scache(@RequestParam(value = "id",defaultValue = "1") String id) {
        CacheTestService cacheTestService = new CacheTestServiceImpl();
        return cacheTestService.get(id);
    }
}
