package com.example.mapper.mybatisMap.dao;

import com.example.mapper.mybatisMap.entity.Orders;
import com.example.mapper.mybatisMap.entity.OrdersCustom;
import com.example.mapper.mybatisMap.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class OrdersMapperCustomIml implements OrdersMapperCustom{
    private Logger logger = LoggerFactory.getLogger(getClass());

    private SqlSessionFactory sessionFactory;
    public OrdersMapperCustomIml(SqlSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<OrdersCustom> findOrdersUser() {
        List<OrdersCustom> list = new ArrayList();
        SqlSession sqlSession = null;
        try {
            sqlSession = sessionFactory.openSession();
            OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
            list = mapper.findOrdersUser();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return  list;
        }
    }

    public List<Orders> findOrdersUserResultMap() {
        List<Orders> list = new ArrayList();
        SqlSession sqlSession = null;
        try {
            sqlSession = sessionFactory.openSession();
            OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
            list = mapper.findOrdersUserResultMap();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return  list;
        }
    }

    public  List<Orders> findOrdersAndOrderDetailResultMap() {
        List<Orders> list = new ArrayList();
        SqlSession sqlSession = null;
        try {
            sqlSession = sessionFactory.openSession();
            OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
            list = mapper.findOrdersAndOrderDetailResultMap();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return  list;
        }
    }
    public List<User> findUsersAndItemsResultMap() {
        List<User> list = new ArrayList();
        SqlSession sqlSession = null;
        try {
            sqlSession = sessionFactory.openSession();
            OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
            list = mapper.findUsersAndItemsResultMap();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return  list;
        }
    }
}
