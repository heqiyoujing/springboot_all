package com.example.mapper.mybatisMap.rabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.LogManager;

public class RabbitMQConfig {
    private static Logger __logger = LoggerFactory.getLogger(RabbitMQConfig.class);
    private String host;
    private String user;
    private String password;
    private int port;
    private String virtual_host;

    public RabbitMQConfig() {
    }

    public static RabbitMQConfig getInstance() {
        return new RabbitMQConfig();
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getVirtual_host() {
        return this.virtual_host;
    }

    public void setVirtual_host(String virtual_host) {
        this.virtual_host = virtual_host;
    }
}
