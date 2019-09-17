package com.example.mapper.mybatisMap.map.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListInfo {
    /**
     * 遍历list的两种方法
     */
    public void forgetList(){
        List<String> list = new ArrayList<>();

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next();
        }

        for(int i=0;i<list.size();i++){
            list.get(i);
        }
    }
}
