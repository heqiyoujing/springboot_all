package com.example.mapper.mybatisMap.httpClient;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.example.mapper.mybatisMap.tool.ExceptionFormat;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequest {
    public static Logger log = LoggerFactory.getLogger(HttpRequest.class.getName());

    public HttpRequest() {
    }

    public static String sendGet(String url, Map<String, Object> params) {
        HttpClient client = new HttpClient();
        if (!params.isEmpty()) {
            url = url + "?";
            Set set = params.entrySet();
            Iterator iterator = set.iterator();

            for(int i = 1; iterator.hasNext(); ++i) {
                Entry mapentry = (Entry)iterator.next();
                if (i == 1) {
                    url = url + mapentry.getKey() + "=" + mapentry.getValue();
                } else {
                    url = url + "&" + mapentry.getKey() + "=" + mapentry.getValue();
                }
            }
        }

        String res = "";
        GetMethod method = new GetMethod(url);

        try {
            client.executeMethod(method);
        } catch (HttpException var8) {
            log.error("请求异常：" + ExceptionFormat.getExceptionMessage(var8));
        } catch (IOException var9) {
            log.error("请求异常：" + ExceptionFormat.getExceptionMessage(var9));
        }

        try {
            res = method.getResponseBodyAsString();
        } catch (IOException var7) {
            log.error("解析响应内容异常：" + ExceptionFormat.getExceptionMessage(var7));
        }

        method.releaseConnection();
        return res;
    }

    public static String sendPost(String url, Map<String, Object> params) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        if (!params.isEmpty()) {
            NameValuePair[] pairParams = new NameValuePair[params.size()];
            Set set = params.entrySet();
            Iterator iterator = set.iterator();

            NameValuePair pairParam;
            for(int var7 = 0; iterator.hasNext(); pairParams[var7++] = pairParam) {
                Entry mapentry = (Entry)iterator.next();
                mapentry.getKey();
                mapentry.getValue();
                pairParam = new NameValuePair(mapentry.getKey().toString(), mapentry.getValue().toString());
            }

            method.setRequestBody(pairParams);
        }

        String res = "";

        try {
            client.executeMethod(method);
        } catch (HttpException var11) {
            log.error("请求异常：" + ExceptionFormat.getExceptionMessage(var11));
        } catch (IOException var12) {
            log.error("请求异常：" + ExceptionFormat.getExceptionMessage(var12));
        }

        try {
            res = method.getResponseBodyAsString();
        } catch (IOException var10) {
            log.error("解析响应内容异常：" + ExceptionFormat.getExceptionMessage(var10));
        }

        method.releaseConnection();
        return res;
    }
}
