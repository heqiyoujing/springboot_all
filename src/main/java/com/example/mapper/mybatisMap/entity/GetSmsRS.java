package com.example.mapper.mybatisMap.entity;

public class GetSmsRS {
    private int userId;
    private String firstCode;
    private int orderId;
    private int code;
    private Enabled enabled;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstCode() {
        return firstCode;
    }

    public void setFirstCode(String firstCode) {
        this.firstCode = firstCode;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Enabled getEnabled() {
        return enabled;
    }

    public void setEnabled(Enabled enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "GetSmsRS{" +
                "userId=" + userId +
                ", firstCode='" + firstCode + '\'' +
                ", orderId=" + orderId +
                ", code=" + code +
                '}';
    }
}
