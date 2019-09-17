package com.example.mapper.mybatisMap.entity;

public class ReturnMsg {
    private Object data;
    private String code;

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getErrorCode() {
        return code;
    }
    public void setErrorCode(String errorCode) {
        this.code = errorCode;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ReturnMsg:{" +
                "Code:"+code+","+
                "data:"+getData()+
                "}";
    }
}
