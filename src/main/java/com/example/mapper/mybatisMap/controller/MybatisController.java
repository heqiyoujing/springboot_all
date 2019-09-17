package com.example.mapper.mybatisMap.controller;

import com.example.mapper.mybatisMap.SpringBootAll;
import com.example.mapper.mybatisMap.dao.OrdersMapperCustom;
import com.example.mapper.mybatisMap.dao.OrdersMapperCustomIml;
import com.example.mapper.mybatisMap.entity.Orders;
import com.example.mapper.mybatisMap.entity.OrdersCustom;
import com.example.mapper.mybatisMap.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: yiqq
 * @date: 2018/7/20
 * @description:
 */

@RestController
public class MybatisController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    OrdersMapperCustom omc = new OrdersMapperCustomIml(SpringBootAll.mybatisMysqlPool.getMasterPool());

    /**
     * 高级查询一对一，resultType
     */
    @RequestMapping(value = "/one/type", method = RequestMethod.GET)
    @ResponseBody
    public List<OrdersCustom> GetOneToOneType(HttpServletRequest request
            , HttpServletResponse response) throws Exception{
        String sessionId = request.getSession().getId();
        System.out.println("-----------" + sessionId);
        return omc.findOrdersUser() ;
    }

    /**
     * 高级查询一对一,resultMap
     */
    @RequestMapping(value = "/one/map", method = RequestMethod.GET)
    @ResponseBody
    public List<Orders> GetOneToOneMap(HttpServletRequest request
            , HttpServletResponse response) throws Exception{
        return omc.findOrdersUserResultMap() ;
    }

    /**
     * 高级查询一对多
     */
    @RequestMapping(value = "/oneduo", method = RequestMethod.GET)
    @ResponseBody
    public List<Orders> GetOneToDuo(HttpServletRequest request
            , HttpServletResponse response) throws Exception{
        return omc.findOrdersAndOrderDetailResultMap();
    }

    /**
     * 高级查询多对多
     */
    @RequestMapping(value = "/duoduo", method = RequestMethod.GET)
    @ResponseBody
    public List<User> GetDuoToDuo(HttpServletRequest request
            , HttpServletResponse response) throws Exception{
        return omc.findUsersAndItemsResultMap();
    }
}
