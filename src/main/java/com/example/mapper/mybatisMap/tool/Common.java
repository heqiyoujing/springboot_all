package com.example.mapper.mybatisMap.tool;

import com.alibaba.fastjson.JSON;
import com.example.mapper.mybatisMap.entity.ReturnMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Common {

    private static Logger logger = LoggerFactory.getLogger(Common.class.getName());

    /**
     * 获取int类型的Requst参数值
     * @param request
     * @param key
     * @return
     */
    public static int getIntParam(HttpServletRequest request, String key) {
        String val = request.getParameter(key);
        int rev = 0;
        try {
            if (val == null || val == "") {
                rev = 0;
            } else {
                rev = Integer.parseInt(val);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rev = 0;
        }
        return rev;
    }
    /**
     * 获取String类型的Requst参数值
     * */
    public static String getStrParam(HttpServletRequest request, String key) {
        return request.getParameter(key);
    }

    /**
     * 获取String类型的Requst参数值
     * @param request
     * @param key
     * @param def
     * @return
     */
    public static String getStrParam(HttpServletRequest request, String key, String def) {
        String val = request.getParameter(key);
        if (val == null || val == "") {
            val = def;
        }
        return val;
    }
    /**
     * 获取Cookie Map
     * @param  request
     * @return Map<String,String>
     */
    public static Map<String,String> getCookieInfo(HttpServletRequest request) {
        Map<String,String> cookieInfo = new HashMap<String,String>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie:cookies) {
                cookieInfo.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieInfo;
    }

    /**
     * 获取请求ip地址
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果是多级代理，那么取第一个ip为客户ip
        /*if (ip != null && ip.indexOf(",") != -1) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
        }*/
        return ip;
    }

    //获取当前服务器IP
    public static String getLocalIp() throws Exception{
        String serverIP = null;
        InetAddress ip = null;
        Enumeration<?> netInterfaces;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                ip = (InetAddress) ni.getInetAddresses().nextElement();
                serverIP = ip.getHostAddress();
                if (ip.isSiteLocalAddress() == true && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {
                    serverIP = ip.getHostAddress();
                    break;
                } else {
                    ip = null;
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return serverIP;
    }

    /**
     * 获取时间差
     * @param startTime
     * @return
     */
    public static int getPayTime(long startTime){
        Long payTime = System.currentTimeMillis() - startTime;
        return payTime.intValue();
    }

    /**
     * 获取一个字符串的MD5值
     * @param str
     * @return
     */
    public static String getMD5(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("SecurityInfo");
            md.update( str.getBytes() );
            String mk5Str = new BigInteger(1, md.digest()).toString(16);

            for (int i = 0; i < 32 - mk5Str.length(); i++) {
                mk5Str = "0" + mk5Str;
            }
            return mk5Str;
        } catch (Exception e){
            logger.error("生成MD5码出错",e);
        }
        return "";
    }
    /**
     * 将json字符串转为ResponseMsg对象
     * @param json String
     * @return ResponseMsg
     */
    public static ReturnMsg jsonToReturnMsg(String json ) {
        ReturnMsg returnMsg = new ReturnMsg();
        try {
            returnMsg =  JSON.parseObject(json,ReturnMsg.class);
        } catch ( Exception e ) {
            logger.error(
                    "jsonToResponseMsg Json:{} Excetpion:{}"
                    , json
            );
        }
        return returnMsg;
    }
    /**
     * json字符串转换为对象
     * @param text
     * @param clazz
     * @return
     */
    public static <T> T parseJsonToObject(String text,Class<T> clazz) {
        T t = null;
        try {
            t = JSON.parseObject(text,clazz);
        } catch (Exception e) {
            logger.error(
                    "parseJsonToObject Json:{} Excetpion:{}"
                    , text
            );
        }
        return t;
    }
    /**
     * 对象转换为json字符串
     * @param object
     * @return
     */
    public static String parseObjectToJson(Object object) {
        String jsonString = "";
        try {
            jsonString = JSON.toJSONString(object);
        } catch (Exception e) {
            logger.error(
                    "parseJsonToObject Object:{} Excetpion:{}"
                    , object
            );
        }
        return jsonString;
    }
    /**
     * json字符串转换为集合
     * @param text
     * @param clazz
     * @return
     */
    public static <T> List<T> parseJsonToArray(String text,Class<T> clazz) {
        List<T> t = null;
        try {
            t = JSON.parseArray(text, clazz);
        } catch (Exception e) {
            logger.error(
                    "parseJsonToObject Json:{} Excetpion:{}"
                    , text
            );
        }
        return t;
    }
}
