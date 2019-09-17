package com.example.mapper.mybatisMap.map.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * https://blog.csdn.net/zzg1229059735/article/details/51498310
 * 如何正确的重写equals() 和 hashCode()方法
 * Created by chengxiao on 2016/11/15.
 */

public class ConHashMap {
    private static class Person{
        int idCard;
        String name;

        public Person(int idCard, String name) {
            this.idCard = idCard;
            this.name = name;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()){
                return false;
            }
            Person person = (Person) o;
            //两个对象是否等值，通过idCard来确定
            return this.idCard == person.idCard;
        }
        @Override
        public int hashCode() {
            return Objects.hash(idCard, name);

        }
        /*@Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + name.hashCode();
            result = 31 * result + idCard;
            return result;
        }*/
        /*@Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(name)
                    .append(idCard)
                    .toHashCode();
        }*/
        /*@Override
        public int hashCode(){
            return this.idCard;
        }*/

    }
    public static void main(String []args){
        HashMap<Person,String> map = new HashMap<Person, String>();
        Person person = new Person(1234,"乔峰");
        //put到hashmap中去
        map.put(person,"天龙八部");
        //get取出，从逻辑上讲应该能输出“天龙八部”
        System.out.println("结果:"+map.get(new Person(1234,"乔峰")));
    }

    /**
     * http://www.cnblogs.com/chengxiao/p/6059914.html#t2  HashMap实现原理及源码分析
     * HashMap是线程不安全的，在并发环境下，可能会形成环状链表（扩容时可能造成）。
     * https://www.cnblogs.com/chengxiao/p/6842045.html  ConcurrentHashMap实现原理及源码分析
     * get方法无需加锁，由于其中涉及到的共享变量都使用volatile修饰，volatile可以保证内存可见性，所以不会读取到过期数据；
     * Segment中的put方法是要加锁的。只不过是锁粒度细了而已。
     */
    public void Coucurrent(){
        Map<String,String> map = new ConcurrentHashMap();

    }
}
