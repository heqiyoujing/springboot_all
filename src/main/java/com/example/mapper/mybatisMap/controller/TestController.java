package com.example.mapper.mybatisMap.controller;

import com.example.mapper.mybatisMap.tool.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: yiqq
 * @date: 2018/7/23
 * @description:
 */
@RestController
@RequestMapping("/servlet")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class.getName());

    @RequestMapping("/clientIp")
    public String ClientIp(HttpServletRequest request, HttpServletResponse response)throws Exception{
        StringBuffer sb = new StringBuffer();
        sb.append(Common.getIpAddress(request)); //0:0:0:0:0:0:0:1
        sb.append("<br/>").append("a");
        return sb.toString();
    }
}
