package com.example.mapper.mybatisMap.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HttpUtil {
    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(1000)  //请求超时时间，接上一个url之后到获取response的返回等待时间
            .setConnectTimeout(1000)  //连接超时时间，取得了连接池中的某个连接之后到接通目标url的连接等待时间
            .setConnectionRequestTimeout(1000)  //从连接池中取连接的超时时间
            .build();
    private static HttpUtil instance = null;
    private HttpUtil(){}

    /**
     * 单例模式获取1个HttpUtil实例
     * @return HttpUtil
     */
    public static HttpUtil getInstance(){
        if (instance == null) {
            instance = new HttpUtil();
        }
        return instance;
    }

    /**
     * HttpPost请求
     * @param url url
     * @param params 参数
     * @param headers 头信息
     * @param encode 编码
     * @return HttpResponse
     */
    public static HttpResponse httpPostForm(String url, Map<String,String> params, Map<String,String> headers, String encode){
        HttpResponse response = new HttpResponse();
        if(encode == null) {
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, encode));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * HttpPost请求 修改请求超时时间
     * @param url url
     * @param params  参数
     * @param headers  头信息
     * @param encode  编码
     * @param socketTimeout   修改socketTimeout
     * @return  HttpResponse
     */
    public static HttpResponse httpPostForm(String url, Map<String,String> params, Map<String,String> headers, String encode, int socketTimeout){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfigModifySocketTimeout = modifyRequestConfig(socketTimeout); //修改RequestConfig的socketTimeout时间
        httpPost.setConfig(requestConfigModifySocketTimeout);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, encode));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 自定义RequestConfig参数
     * @param url url地址
     * @param params 参数
     * @param headers 头信息
     * @param encode 编码
     * @param requestConfig 连接配置
     * @return HttpResponse
     */
    public static HttpResponse httpPostForm(String url, Map<String,String> params, Map<String,String> headers, String encode, RequestConfig requestConfig){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(),entry.getValue());
            }
        }
        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, encode));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    /**
     * httpGet 请求
     * @param url url地址
     * @param params 参数map
     * @param headers 头信息
     * @param encode 编码信息
     * @return HttpResponse
     */
    public static HttpResponse httpGetForm(String url,Map<String,String> params, Map<String,String> headers,String encode){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //组织请求参数，list的数据类型是NameValuePair（简单名称值对节点类型），这个代码多处用于Java向url发送Post请求
        List<NameValuePair> paramList = new ArrayList <NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        String getQuery=null;
        try {
            getQuery = EntityUtils.toString(new UrlEncodedFormEntity(paramList, encode));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if ( getQuery != null && !getQuery.equals("")) {
            url = url + "?" + getQuery;
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(),entry.getValue());
            }
        }
        String content = null;
        CloseableHttpResponse  httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * httpGet 请求 修改socketTimeout
     * @param url  url地址
     * @param params  参数map
     * @param headers  头信息
     * @param encode  编码信息
     * @param socketTimeout  修改socketTimeout
     * @return  HttpResponse
     */
    public static HttpResponse httpGetForm(String url,Map<String,String> params, Map<String,String> headers,String encode, int socketTimeout){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //组织请求参数，list的数据类型是NameValuePair（简单名称值对节点类型），这个代码多处用于Java向url发送Post请求
        List<NameValuePair> paramList = new ArrayList <NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        String getQuery=null;
        try {
            getQuery = EntityUtils.toString(new UrlEncodedFormEntity(paramList, encode));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if ( getQuery != null && !getQuery.equals("")) {
            url = url + "?" + getQuery;
        }
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfigModifySocketTimeout = modifyRequestConfig(socketTimeout); //修改RequestConfig的socketTimeout时间
        httpGet.setConfig(requestConfigModifySocketTimeout);
        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(),entry.getValue());
            }
        }
        String content = null;
        CloseableHttpResponse  httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * httpGet 请求 -- 自定义Config方式
     * @param url url地址
     * @param params 参数map
     * @param headers 头信息
     * @param encode 编码信息
     * @return HttpResponse
     */
    public static HttpResponse httpGetForm(String url,Map<String,String> params, Map<String,String> headers,String encode,RequestConfig requestConfig){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //组织请求参数
        List<NameValuePair> paramList = new ArrayList <NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        String getQuery=null;
        try {
            getQuery = EntityUtils.toString(new UrlEncodedFormEntity(paramList, encode));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if ( getQuery != null && !getQuery.equals("")) {
            url = url + "?" + getQuery;
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(),entry.getValue());
            }
        }
        String content = null;
        CloseableHttpResponse  httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    /**
     * http-delete 请求
     * @param url 请求地址
     * @param headers 请求头信息
     * @param encode 编码
     * @return HttpResponse
     */
    public static HttpResponse httpDelete(String url,  Map<String,String> headers, String encode) {
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient  = HttpClientBuilder.create().build();
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setConfig(requestConfig);
        if ( headers != null && headers.size() > 0 ) {
            for ( String key :headers.keySet() ){
                httpDelete.setHeader( key, headers.get(key) );
            }
        }

        CloseableHttpResponse closeableHttpResponse = null;
        try {
            closeableHttpResponse = closeableHttpClient.execute(httpDelete);
            HttpEntity entity = closeableHttpResponse.getEntity();
            String content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setStatusCode(closeableHttpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (closeableHttpResponse!=null )
                    closeableHttpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * http-delete 请求 修改socketTimeout
     * @param url  u请求地址
     * @param headers  头信息
     * @param encode  编码信息
     * @param socketTimeout 修改socketTimeout
     * @return  HttpResponse
     */
    public static HttpResponse httpDelete(String url,  Map<String,String> headers, String encode, int socketTimeout) {
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient  = HttpClientBuilder.create().build();
        HttpDelete httpDelete = new HttpDelete(url);
        RequestConfig requestConfigModifySocketTimeout = modifyRequestConfig(socketTimeout); //修改RequestConfig的socketTimeout时间
        httpDelete.setConfig(requestConfigModifySocketTimeout);
        if ( headers != null && headers.size() > 0 ) {
            for ( String key :headers.keySet() ){
                httpDelete.setHeader( key, headers.get(key) );
            }
        }

        CloseableHttpResponse closeableHttpResponse = null;
        try {
            closeableHttpResponse = closeableHttpClient.execute(httpDelete);
            HttpEntity entity = closeableHttpResponse.getEntity();
            String content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setStatusCode(closeableHttpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (closeableHttpResponse!=null )
                    closeableHttpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * httpDelete 以Query方式传递参数 : 注意url最大长度，参数过多请使用 httpDeleteWithJson 方法
     * @param url url
     * @param params 参数map
     * @param headers 头信息
     * @param encode 解码字符集
     * @return HttpResponse
     */
    public static HttpResponse httpDeleteFormWithQuery(String url, Map<String,String> params, Map<String,String> headers, String encode){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }

        String getQuery=null;
        try {
            getQuery = EntityUtils.toString(new UrlEncodedFormEntity(paramList, encode));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if ( getQuery != null && !getQuery.equals("")) {
            url = url + "?" + getQuery;
        }

        HttpDeleteWithBody httpDeleteWithBody = new HttpDeleteWithBody(url);
        httpDeleteWithBody.setConfig(requestConfig);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpDeleteWithBody.setHeader(entry.getKey(),entry.getValue());
            }
        }

        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpDeleteWithBody);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * httpDelete 以Query方式传递参数 : 注意url最大长度，参数过多请使用 httpDeleteWithJson 方法  修改socketTimeout
     * @param url  请求地址
     * @param params  参数
     * @param headers  头信息
     * @param encode  解码字符集
     * @param socketTimeout  修改socketTimeout
     * @return  httpReponse
     */
    public static HttpResponse httpDeleteFormWithQuery(String url, Map<String,String> params, Map<String,String> headers, String encode, int socketTimeout){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }

        String getQuery=null;
        try {
            getQuery = EntityUtils.toString(new UrlEncodedFormEntity(paramList, encode));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if ( getQuery != null && !getQuery.equals("")) {
            url = url + "?" + getQuery;
        }

        HttpDeleteWithBody httpDeleteWithBody = new HttpDeleteWithBody(url);
        RequestConfig requestConfigModifySocketTimeout = modifyRequestConfig(socketTimeout); //修改RequestConfig的socketTimeout时间
        httpDeleteWithBody.setConfig(requestConfigModifySocketTimeout);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpDeleteWithBody.setHeader(entry.getKey(),entry.getValue());
            }
        }

        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpDeleteWithBody);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 自定义Config
     * httpDelete 以Query方式传递参数 : 注意url最大长度，参数过多请使用 httpDeleteWithJson 方法
     * @param url url
     * @param params 参数map
     * @param headers 头信息
     * @param encode 解码字符集
     * @return HttpResponse
     */
    public static HttpResponse httpDeleteFormWithQuery(String url, Map<String,String> params, Map<String,String> headers, String encode,RequestConfig requestConfig){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();

        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }

        String getQuery=null;
        try {
            getQuery = EntityUtils.toString(new UrlEncodedFormEntity(paramList, encode));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if ( getQuery != null && !getQuery.equals("")) {
            url = url + "?" + getQuery;
        }

        HttpDeleteWithBody httpDeleteWithBody = new HttpDeleteWithBody(url);
        httpDeleteWithBody.setConfig(requestConfig);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpDeleteWithBody.setHeader(entry.getKey(),entry.getValue());
            }
        }

        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpDeleteWithBody);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }




    /**
     * httpDelete 带有body方式
     * @param url url
     * @param json 对象json
     * @param headers 头信息
     * @param encode 解码字符集
     * @return HttpResponse
     */
    public static HttpResponse httpDeleteWithJson(String url, String json, Map<String,String> headers, String encode){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpDeleteWithBody httpDeleteWithBody = new HttpDeleteWithBody(url);
        httpDeleteWithBody.setConfig(requestConfig);
        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpDeleteWithBody.setHeader(entry.getKey(),entry.getValue());
            }
        }
        httpDeleteWithBody.setHeader(new BasicHeader("Content-Type", "application/json;charset=utf-8"));
        try {
            httpDeleteWithBody.setEntity(new StringEntity(json));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpDeleteWithBody);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * httpDelete 带有body方式  修改socketTimeout
     * @param url  请求参数
     * @param json  对象json
     * @param headers  头信息
     * @param encode  解码字符集
     * @param socketTimeout  修改socketTimeout
     * @return  httpResponse
     */
    public static HttpResponse httpDeleteWithJson(String url, String json, Map<String,String> headers, String encode, int socketTimeout){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpDeleteWithBody httpDeleteWithBody = new HttpDeleteWithBody(url);
        RequestConfig requestConfigModifySocketTimeout = modifyRequestConfig(socketTimeout); //修改RequestConfig的socketTimeout时间
        httpDeleteWithBody.setConfig(requestConfigModifySocketTimeout);
        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpDeleteWithBody.setHeader(entry.getKey(),entry.getValue());
            }
        }
        httpDeleteWithBody.setHeader(new BasicHeader("Content-Type", "application/json;charset=utf-8"));
        try {
            httpDeleteWithBody.setEntity(new StringEntity(json));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpDeleteWithBody);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }


    /**
     * httpDelete 带有body方式 自定义Config信息
     * @param url url
     * @param json 对象json
     * @param headers 头信息
     * @param encode 解码字符集
     * @return HttpResponse
     */
    public static HttpResponse httpDeleteWithJson(String url, String json, Map<String,String> headers, String encode,RequestConfig requestConfig){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpDeleteWithBody httpDeleteWithBody = new HttpDeleteWithBody(url);
        httpDeleteWithBody.setConfig(requestConfig);
        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpDeleteWithBody.setHeader(entry.getKey(),entry.getValue());
            }
        }
        httpDeleteWithBody.setHeader(new BasicHeader("Content-Type", "application/json;charset=utf-8"));
        try {
            httpDeleteWithBody.setEntity(new StringEntity(json));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpDeleteWithBody);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }




    /**
     * HttpPut
     * @param url url
     * @param params 参数map
     * @param headers 头信息
     * @param encode 解码字符集
     * @return HttpResponse
     */
    public static HttpResponse httpPutForm(String url, Map<String,String> params, Map<String,String> headers, String encode){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(requestConfig);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPut.setHeader(entry.getKey(),entry.getValue());
            }
        }

        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        try {
            httpPut.setEntity(new UrlEncodedFormEntity(paramList, encode));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpPut);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * HttpPut 修改socketTimeout
     * @param url 请求参数
     * @param params  参数
     * @param headers  头信息
     * @param encode  解码字符集
     * @param socketTimeout  修改socketTimeout
     * @return  httpResponse
     */
    public static HttpResponse httpPutForm(String url, Map<String,String> params, Map<String,String> headers, String encode, int socketTimeout){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        RequestConfig requestConfigModifySocketTimeout = modifyRequestConfig(socketTimeout); //修改RequestConfig的socketTimeout时间
        httpPut.setConfig(requestConfigModifySocketTimeout);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPut.setHeader(entry.getKey(),entry.getValue());
            }
        }

        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        try {
            httpPut.setEntity(new UrlEncodedFormEntity(paramList, encode));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpPut);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * HttpPut 自定义Config
     * @param url url
     * @param params 参数map
     * @param headers 头信息
     * @param encode 解码字符集
     * @return HttpResponse
     */
    public static HttpResponse httpPutForm(String url, Map<String,String> params, Map<String,String> headers, String encode,RequestConfig requestConfig){
        HttpResponse response = new HttpResponse();
        if(encode == null){
            encode = "utf-8";
        }
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setConfig(requestConfig);

        //设置header
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPut.setHeader(entry.getKey(),entry.getValue());
            }
        }

        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        try {
            httpPut.setEntity(new UrlEncodedFormEntity(paramList, encode));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String content = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpPut);
            HttpEntity entity = httpResponse.getEntity();
            content = EntityUtils.toString(entity, encode);
            response.setBody(content);
            response.setHeaders(httpResponse.getAllHeaders());
            response.setReasonPhrase(httpResponse.getStatusLine().getReasonPhrase());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if ( httpResponse != null )
                    httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 修改RequestConfig的socketTimeout请求超时时间
     * @param socketTimeout  参数
     * @return  requestConfig
     */
    private static RequestConfig modifyRequestConfig (int socketTimeout) {
        RequestConfig requestConfigModifySocketTimeOut;
        if (socketTimeout > 0) {
            requestConfigModifySocketTimeOut = RequestConfig.custom()
                    .setSocketTimeout(socketTimeout)  //重新设定请求超时时间
                    .setConnectTimeout(requestConfig.getConnectTimeout())  //获取之前的连接超时时间
                    .setConnectionRequestTimeout(requestConfig.getConnectionRequestTimeout())  //获取之前的从连接池中取连接的超时时间
                    .build();
        } else{
            requestConfigModifySocketTimeOut = requestConfig;
        }
        return requestConfigModifySocketTimeOut;
    }
}
