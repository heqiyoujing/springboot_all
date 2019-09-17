package com.example.mapper.mybatisMap.dao;

import com.example.mapper.mybatisMap.entity.OOO;
import com.example.mapper.mybatisMap.entity.Student;
import com.example.mapper.mybatisMap.entity.User;
import com.example.mapper.mybatisMap.entity.User_c;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yiqq
 * @date: 2018/7/20
 * @description:
 */
public class UserDaoIml implements UserDao{
    private Logger logger = LoggerFactory.getLogger(getClass());

    private SqlSessionFactory sessionFactory;
    public UserDaoIml(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ArrayList<User> findInfoList(@Param("name") String name, @Param("age") int age) {
        SqlSession sqlSession = null;
        ArrayList<User> users = new ArrayList();
        try {
            sqlSession = sessionFactory.openSession();
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            users = mapper.findInfoList(name, age);
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return  users;
        }
    }

    public Integer findCount(@Param("name") String name, @Param("age") int age) {
        SqlSession sqlSession = null;
        int users =0;
        try {
            sqlSession = sessionFactory.openSession();
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            users = mapper.findCount(name, age);
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return  users;
        }
    }

    public String findName(User user) {
        SqlSession sqlSession = null;
        String nameR =null;
        try {
            sqlSession = sessionFactory.openSession();
            nameR = sqlSession.selectOne("com.example.mapper.mybatisMap.dao.UserDao.findName", user);
            return nameR;
        } catch (Exception e) {
            e.printStackTrace();
            return  nameR;
        }
    }

    public boolean insert(User user) {
        SqlSession sqlSession = null;
        boolean res =false;
        try {
            sqlSession = sessionFactory.openSession();
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            res = mapper.insert(user);
            if( res == true) {
                sqlSession.commit();
                res = true;
            } else {
                sqlSession.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<User> selectall() {
        SqlSession sqlSession = null;
        List<User> res =new ArrayList<>();
        try {
            sqlSession = sessionFactory.openSession();
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            res = mapper.selectall();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean insertTime(User_c user) {
        SqlSession sqlSession = null;
        boolean res =false;
        try {
            sqlSession = sessionFactory.openSession();
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            res = mapper.insertTime(user);
            if( res == true) {
                sqlSession.commit();
                res = true;
            } else {
                sqlSession.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<Student> selectuser_t() {
        SqlSession sqlSession = null;
        List<Student> res =new ArrayList<>();
        try {
            sqlSession = sessionFactory.openSession();
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            res = mapper.selectuser_t();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public List<OOO> selectallname() {
        SqlSession sqlSession = null;
        List<OOO> res =new ArrayList<>();
        try {
            sqlSession = sessionFactory.openSession();
            UserDao mapper = sqlSession.getMapper(UserDao.class);
            res = mapper.selectallname();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
