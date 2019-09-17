package com.example.mapper.mybatisMap.map.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class SetInfo {

    /**
     * set集合，遍历集合
     */
    public void Set(){
        String txt = "首先，项目收集到的敏感词有几千条，使用a方案肯定不行。其次，为了方便以后的扩展性尽量减少对数据库" +
                "的依赖，所以放弃b方案。然后Lucene本身作为本地索引，敏感词增加后需要触发更新索引，并且这里本着轻量原则" +
                "不想引入更多的库，所以放弃c方案。于是我们选定d方案为研究目标。";
        String replaceChar = "*";
        Set<String> set = new HashSet<>();
        set.add("敏感词");
        set.add("扩展性");
        set.add("Lucene");
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            txt = txt.replaceAll(word, replaceString);
        }
        System.out.println(txt);
    }

    /**
     * 替换，用*替换字符串
     * @param replaceChar
     * @param length
     * @return
     */
    private String getReplaceChars(String replaceChar,int length){
        String resultReplace = replaceChar;  //不建议用它，建议用StringBuffer.
        try {
            for (int i = 1; i < length; i++) {
                resultReplace += replaceChar;
            }
        } catch (Exception e ){
            e.printStackTrace();
             }
        return resultReplace;
    }
}
