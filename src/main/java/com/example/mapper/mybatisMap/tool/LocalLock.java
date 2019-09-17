package com.example.mapper.mybatisMap.tool;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class LocalLock {
    private String key;
    private static ConcurrentHashMap<String, Object> readLockMap = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Object> writeLockMap = new ConcurrentHashMap<>();
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String sessionId;
    /*并发编程实践中，ConcurrentHashMap是一个经常被使用的数据结构，相比于Hashtable以及Collections.synchronizedMap()，
     ConcurrentHashMap在线程安全的基础上提供了更好的写并发能力，但同时降低了对读一致性的要求（这点好像CAP理论啊 O(∩_∩)O）。
     ConcurrentHashMap的设计与实现非常精巧，大量的利用了volatile，final，CAS等lock-free技术来减少锁竞争对于性能的影响，
     无论对于Java并发编程的学习还是Java内存模型的理解，ConcurrentHashMap的设计以及源码都值得非常仔细的阅读与揣摩。*/
    public LocalLock (String sessionId, String key) {
        this.sessionId = sessionId;
        this.key = key;
    }
    public static LocalLock getInstance(String sessionId, String key) {
        return new LocalLock(sessionId, key);
    }
    /**
     * 加写锁
     * @param time
     * @return
     */
    public boolean writeLock (int time) {
        if(time < 0) {
            time = 0;
        } else if(time >3600) {
            time = 300;
        }
        Long stratTime = System.currentTimeMillis();
        while (true) {
            // 读、写锁map同时不存在key，加锁成功，返回true,否则返回false
            if( !writeLockMap.containsKey(key) && !readLockMap.containsKey(key)) {
                writeLockMap.put(key, 10);
                return true;
            }
            // time秒后停止检测，返回false
            if((System.currentTimeMillis() - stratTime) > time * 1000) {
                return false;
            }
        }
    }
    /**
     * 释放锁
     */
    public void unWriteLock () {
        try{
            writeLockMap.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("SessionId:[%s]\tException:[%s]", this.sessionId, e);
        }
    }
    /**
     * 读加锁
     * @param time
     * @return
     */
    public boolean readLock (int time) {
        if(time < 0) {
            time = 0;
        } else if(time >3600) {
            time = 300;
        }
        Long stratTime = System.currentTimeMillis();
        while (true) {
            // 读锁map不存在key，加锁成功,返回true,否则返回false
            if( !readLockMap.containsKey(key)) {
                readLockMap.put(key, 1);
                return true;
            }
            // time秒后停止检测，返回false
            if((System.currentTimeMillis() - stratTime) > time * 1000) {
                return false;
            }
        }
    }
    /**
     * 释放读锁
     */
    public void unReadLock () {
        try {
            readLockMap.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
