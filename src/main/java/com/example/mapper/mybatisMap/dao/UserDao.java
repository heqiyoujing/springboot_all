package com.example.mapper.mybatisMap.dao;

import com.example.mapper.mybatisMap.entity.OOO;
import com.example.mapper.mybatisMap.entity.Student;
import com.example.mapper.mybatisMap.entity.User;
import com.example.mapper.mybatisMap.entity.User_c;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yiqq
 * @date: 2018/7/20
 * @description:
 */
public interface UserDao {
    ArrayList<User> findInfoList(@Param("name") String name, @Param("age") int age);

    Integer findCount(@Param("name") String name, @Param("age") int age);

    String findName(User user);

    boolean insert(User user);

    List<User> selectall();

    boolean insertTime(User_c user);

    List<Student> selectuser_t();


    List<OOO> selectallname();


}
