package com.example.mapper.mybatisMap.controller;


import com.example.mapper.mybatisMap.entity.User;
import com.example.mapper.mybatisMap.redis.redisLock.RedisTool;
import com.example.mapper.mybatisMap.redis.redisPool.RedisConnPool;
import com.example.mapper.mybatisMap.redis.redistemplate.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
public class RedisController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //RedisTemplate还提供了对应的*OperationsEditor，用来通过RedisTemplate直接注入对应的Operation。
    //声明


    @Autowired
    private RedisUtil redisUtil;


    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private StringRedisTemplate template;

    /**
     * redisTemplate存入对象，对象需要序列化，redis存入的值是序列化之后的，key拼接过。
     * 可以直接存json字符串，能直接看见。
     */
    @RequestMapping(value = "redis/object")
    public User tsetObject(){
        User user = new User();
        user.setId(11);
        user.setName("yi");
        user.setEmail("123456");
        user.setAge(22);
        redisTemplate.opsForValue().set("user", user);
        User users = (User) redisTemplate.opsForValue().get("user");
        if(users == null) {
            user.setName("出现null");
            return user;
        }
        return users;
    }


    /**
     * redis采用管道与正常储存效率
     * @return
     */
    @RequestMapping(value = "redis/diff")
    public String tsetRedis(){
        Long time = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            stringRedisTemplate.opsForValue().set("yi" + i, "wo" + i);
        }
        Long time1 = System.currentTimeMillis();
        System.out.println("耗时：" + (time1 - time));
        long time4 = System.currentTimeMillis();
        stringRedisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                for (int i = 0; i < 10000; i++) {
                    stringRedisTemplate.opsForValue().set("qiang" + i, "wo" + i);
                }
                return null;  //RedisTemplate执行executePipelined方法是有返回值的
            }
        });
        Long time2 = System.currentTimeMillis();
        System.out.println("耗时：" + (time2 - time4));
        return "redis正常耗时：" + (time1 - time) + "<br/>" + "redis管道耗时：" + (time2 - time4);
        /*stringRedisTemplate.opsForValue().set("yi", "yiqq");
        return "success";*/
    }
    /**
     * jedis操作reidis
     * @return
     */
    @RequestMapping(value = "jedis")
    public String iedis(){
        String out = "error";
        JedisPool redisPool = RedisConnPool.getInstance().getRedisPool();
        if(redisPool == null ){
            out = "redisPool为空";
        }
        Jedis redis = redisPool.getResource();
        if(redis == null ){
            out = "redis为空";
        }
        redis.set("yi","qiangqiang");
        String msg = redis.get("yi");
        if(msg == null || msg.equals("")){
            out = "msg为null";
        }else{
            out = msg;
        }
        return out;
    }

    /**
     * 分布式锁
     */
    @RequestMapping(value = "lll")
    public String chh(){
        Jedis jedis = RedisConnPool.getInstance().getRedisPool().getResource();
        String lockKey = "lock_"+ "yiqq";
        String sessiond = UUID.randomUUID().toString();
        boolean flag = RedisTool.tryGetDistributedLock(jedis, lockKey,sessiond, 100);
        if(flag){

        }
        boolean fff = RedisTool.releaseDistributedLock(jedis, lockKey,sessiond);
        if(fff){

        }
        return "";
    }


    /**
     * 从redis找出一个value
     * @return
     */
    @RequestMapping("/getredis")
    public String mm(){
        String sss = "-1";
        long timeend1 = System.currentTimeMillis();
        String value=(String)redisTemplate.opsForHash().get("map1","guava做cache时候数据的移除方式"+9*76854);
        if(value != null && !value.equals("")){
            sss = "花费秒：";
        }
        long timeend2 = System.currentTimeMillis();
        long ssss = (timeend2-timeend1);
        return "从redis找出，需要豪秒："+ ssss ;
    }
    /**
     * list转换为map,写进redis
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/setsss")
    public String sss(HttpServletRequest request, HttpServletResponse response)throws Exception{
        long time = System.currentTimeMillis();
        String sss = "-1:";
        Map<String,String> map=new HashMap<String,String>();
        long timestart = System.currentTimeMillis();
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 100000; j++) {
                map.put("guava做cache时候数据的移除方式"+i*j,"guava做cache时候数据的移除方式"+i*j);
            }
        }
        long timeend = System.currentTimeMillis();
        redisTemplate.opsForHash().putAll("map1",map);
        long timeend1 = System.currentTimeMillis();
        long ss = (timeend-timestart);
        long sss1 = (timeend1-timeend);
        long ssssss = timeend1 - timestart;
        return map.size() + "条的list集合转化为map,需要毫秒：" + ss + " <br/>   set进redis,需要豪秒："+ sss1+"  <br/> 总共需要毫秒："+ ssssss;
    }


    @RequestMapping("/set1")
    public String callset(HttpServletRequest request){
        /*String msg ="success";
        stringRedisTemplate.opsForValue().set("testpack", "100",60*10, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
        msg = stringRedisTemplate.opsForValue().get("testpack"); //根据key获取缓存中的val*/
        //msg =  new RedisUtil().sget("testpack");
        User user = new User();
        user.setAge(25);
        user.setName("yiqq");
        redisUtil.setRedisTemplate("sssss", user);
        return "success";
    }

    @RequestMapping("/get1")
    public String callsget(HttpServletRequest request){
        User user = new User();
        user = (User) redisUtil.getRedisTemplate("ssss"); //根据key获取缓存中的val
        return user.getName();
    }



}
