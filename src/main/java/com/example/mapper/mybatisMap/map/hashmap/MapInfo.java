package com.example.mapper.mybatisMap.map.hashmap;



import com.example.mapper.mybatisMap.entity.User;

import java.util.HashMap;
import java.util.Map;

public class MapInfo {

    /**
     * Map集合，获取key和value
     */
    public void Map(){
        Map<String,Integer> words = new HashMap<>();
        words.put("yiqq",27);
        if( words != null ){
            for (Map.Entry<String, Integer> entry : words.entrySet()){
                User user = new User();
                user.setName(entry.getKey());
                user.setAge(entry.getValue());
            }
        }
    }

}
