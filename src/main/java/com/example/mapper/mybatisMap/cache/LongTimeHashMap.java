package com.example.mapper.mybatisMap.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yiqq
 * @date @date 2018/07/20 14:54
 * ConcurrentHashMap当作缓存
 */
public class LongTimeHashMap {

    private static ConcurrentHashMap<String, LongTime> map = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, LongTime> getMap(){
        return map;
    }

    public void setHashMapCache(ConcurrentHashMap<String, LongTime> map){
        this.map = map;
    }

    public LongTime getLongTime(String key){
        return map.get(key);
    }

    public void setLongTime(String key, LongTime longTime){
        map.put(key, longTime);

    }
}
