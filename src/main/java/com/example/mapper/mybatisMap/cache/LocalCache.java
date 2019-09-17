package com.example.mapper.mybatisMap.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;
/**
 * @author yiqq
 * @date @date 2018/07/20 14:44
 * guava缓存cache
 */
public class LocalCache {
    private static Cache<String, Integer> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(10L, TimeUnit.MINUTES)
            .maximumSize(50000L)
            .build();

    public Integer getCache(String key){
        return (Integer)cache.getIfPresent(key);
    }

    public Cache<String, Integer> getCache(){
        return cache;
    }

    public void setCache(String key, Integer obj){
        cache.put(key, obj);
    }

    public void removeCache(String key){
        cache.invalidate(key);
    }

    public void removeAll(){
        cache.invalidateAll();
    }

    public long getSize(){
        return cache.size();
    }


}
