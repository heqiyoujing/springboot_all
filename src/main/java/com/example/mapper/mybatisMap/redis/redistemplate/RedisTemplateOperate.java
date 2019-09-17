package com.example.mapper.mybatisMap.redis.redistemplate;

import com.alibaba.fastjson.JSON;
import com.example.mapper.mybatisMap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisTemplateOperate {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate template;

    /**
     * redis储存set
     */
    @RequestMapping("/set")
    public void redisSet(){
        Set<String> set=new HashSet<String>();
        set.add("111");
        set.add("222");
        set.add("333");
        redisTemplate.opsForSet().add("set",set);
        Set<String> resultSet =redisTemplate.opsForSet().members("set");
        redisTemplate.expire("set", 1, TimeUnit.SECONDS);
        System.out.println("resultSet:"+resultSet);
    }

    /**
     * redis储存map
     */
    @RequestMapping("/map")
    public void redisMap(){
        Map<String,String> map=new HashMap<String,String>();
        map.put("111","111");
        map.put("222","222");
        map.put("333","333");
        map.put("444","444");
        map.put("555","555");
        redisTemplate.opsForHash().putAll("map",map);
        Map<String,String> resultMap= redisTemplate.opsForHash().entries("map");
        List<String> reslutMapList=redisTemplate.opsForHash().values("map");
        Set<String>resultMapSet=redisTemplate.opsForHash().keys("map");
        String value=(String)redisTemplate.opsForHash().get("map","111");
        System.out.println("value:"+value);
        System.out.println("resultMapSet:"+resultMapSet);
        System.out.println("resultMap:"+resultMap);
        System.out.println("resulreslutMapListtMap:"+reslutMapList);
    }

    /**
     * redis储存list
     */
    @RequestMapping("/list")
    public void redisList(){
        List<String> list1=new ArrayList<String>();
        list1.add("111");
        list1.add("222");
        list1.add("333");

        List<String> list2=new ArrayList<String>();
        list2.add("444");
        list2.add("555");
        list2.add("666");
        redisTemplate.opsForList().leftPush("list1",list1);
        redisTemplate.opsForList().rightPush("list2",list2);
        List<String> resultList1=(List<String>)redisTemplate.opsForList().leftPop("list1");
        List<String> resultList2=(List<String>)redisTemplate.opsForList().rightPop("list2");
        System.out.println("resultList1:"+resultList1);
        System.out.println("resultList2:"+resultList2);
    }

    /**
     * redis储存key-value
     */
    @RequestMapping("/key/value")
    public void redisKeyValue(){
        System.out.println("缓存正在设置。。。。。。。。。");
        redisTemplate.opsForValue().set("111","111");
        redisTemplate.opsForValue().set("222","222");
        redisTemplate.opsForValue().set("333","333");
        redisTemplate.opsForValue().set("444","444");
        System.out.println("缓存已经设置完毕。。。。。。。");
        String result1=redisTemplate.opsForValue().get("111").toString();
        String result2=redisTemplate.opsForValue().get("222").toString();
        String result3=redisTemplate.opsForValue().get("333").toString();
        System.out.println("缓存结果为：result："+result1+"  "+result2+"   "+result3);
    }

    /**
     * redis储存对象
     */
    @RequestMapping("/Object")
    public void redisObject(){
        User user = new User();
        user.setId(11);
        user.setName("yi");
        user.setPhone("123456");
        user.setAge(22);
        redisTemplate.opsForValue().set("user", user);
        User users = (User) redisTemplate.opsForValue().get("user");
        System.out.println(users);
    }

    /**
     * 存储json字符串
     */
    @RequestMapping("/template")
    public void StringRedisTemplate(){
        User user = new User();
        user.setId(11);
        user.setName("yi");
        user.setPhone("123456");
        user.setAge(22);
        template.opsForValue().set("user", JSON.toJSONString(user));
        String str = template.opsForValue().get("user");
        User userName = JSON.parseObject(str, User.class);
        System.out.println(userName);
    }




}
