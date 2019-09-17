package com.example.mapper.mybatisMap.service;

import com.example.mapper.mybatisMap.dao.UserDao;
import com.example.mapper.mybatisMap.entity.User;
import com.example.mapper.mybatisMap.tool.DBTools;
import org.apache.ibatis.session.SqlSession;
import java.util.ArrayList;

/**
 * @author yiqq
 * @date 2017/07/20
 * 不建议使用的方式，连接数据库
 */
public class UserService {
    public ArrayList<User> selectGoodsByGoodId(String name, int age){
        SqlSession session = DBTools.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        ArrayList<User> listName = userDao.findInfoList(name, age);
        return listName;
    }

    public Integer selectCount(String name, int age){
        SqlSession session = DBTools.getSession();
        UserDao userDao = session.getMapper(UserDao.class);
        int listName = userDao.findCount(name, age);
        return listName;
    }

}
