package com.example.mapper.mybatisMap.controller;

import com.alibaba.fastjson.JSON;
import com.example.mapper.mybatisMap.entity.Usersss;
import com.example.mapper.mybatisMap.httpClient.HttpResponse;
import com.example.mapper.mybatisMap.httpClient.HttpUtil;
import com.example.mapper.mybatisMap.tool.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yiqq
 * @date: 2018/7/20
 * @description:
 */

@RestController
public class HttpController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * http返回list
     */
    @RequestMapping(value = "/getHttp_I", method = RequestMethod.GET)
    @ResponseBody
    public List<Usersss> GetUserInfo11 (HttpServletRequest request
            , HttpServletResponse response) throws Exception{
        List<Usersss> userList = new ArrayList<>();
        String url = "http://192.168.16.107:8081/select/all";
        HashMap<String, String> getParamsMap = new HashMap<>();
        HttpResponse httpGetForm = HttpUtil.getInstance().httpGetForm(
                url
                , getParamsMap
                , null
                , null
        );
        return Common.parseJsonToArray(httpGetForm.getBody(),Usersss.class );
    }

    /**
     * http返回MAP对象获取其中字符串
     * get请求
     */
    @RequestMapping(value = "/getHttp_c", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> GetUserInfo1 (HttpServletRequest request
            , HttpServletResponse response) throws Exception{
        Map<String, Object> map = new HashMap<>();
        String url = "http://192.168.16.107:8081/map";
        HashMap<String, String> getParamsMap = new HashMap<>();
        getParamsMap.put("data", "success");
        HttpResponse httpGetForm = HttpUtil.getInstance().httpGetForm(
                url
                , getParamsMap
                , null
                , null
        );
        Object object = JSON.parse(httpGetForm.getBody());    //先转换成Object
        map = (Map)object;
        return map;
    }
}
