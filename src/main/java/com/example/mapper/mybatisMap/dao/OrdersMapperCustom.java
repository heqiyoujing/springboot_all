package com.example.mapper.mybatisMap.dao;


import com.example.mapper.mybatisMap.entity.Orders;
import com.example.mapper.mybatisMap.entity.OrdersCustom;
import com.example.mapper.mybatisMap.entity.User;

import java.util.List;

public interface OrdersMapperCustom {
    /**
     * 一对一
     * @return
     */
    List<OrdersCustom> findOrdersUser();

    /**
     * 一对一
     * @return
     */
    List<Orders> findOrdersUserResultMap();

    /**
     * 一对多
     * @return
     */
    List<Orders> findOrdersAndOrderDetailResultMap();

    /**
     * 多对多
     * @return
     */
    List<User> findUsersAndItemsResultMap();
}
