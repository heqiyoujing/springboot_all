package com.example.mapper.mybatisMap.rabbitMQ;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQ {
    private static ConnectionFactory MQfactory;
    private static Connection MQconnection;
    private RabbitMQConfig config;
    private static Logger logger = LoggerFactory.getLogger(RabbitMQ.class.getName());
    public RabbitMQ() {
    }
    public static RabbitMQ getInstance() {
        return new RabbitMQ();
    }
    public void init(RabbitMQConfig config) throws IOException, TimeoutException {
        this.setConfig(config);
        MQfactory = new ConnectionFactory();
        MQfactory.setUsername(this.getConfig().getUser());
        MQfactory.setPassword(this.getConfig().getPassword());
        MQfactory.setVirtualHost(this.getConfig().getVirtual_host());
        MQfactory.setHost(this.getConfig().getHost());
        MQfactory.setPort(this.getConfig().getPort());
        MQconnection = MQfactory.newConnection();
    }
    public static Channel getMQChannel(Connection mqConn) {
        Channel mqChannel = null;
        try {
            logger.debug("getMQChannelFirstStart");
            mqChannel = mqConn.createChannel();
            if (mqChannel == null) {
                logger.debug("getMQChannelFirstNull\t" + MQconnection);
                if (MQconnection != null) {
                    MQconnection.close();
                }
                MQconnection = MQfactory.newConnection();
                mqChannel = MQconnection.createChannel();
            }
            logger.debug("getMQChannelFirstEnd\tmqChannel:" + mqChannel);
        } catch (Exception var5) {
            logger.warn("getMQChannel1Debug");
            logger.warn("getMQChannel1Debug\tconn:[%s]", new Object[]{mqConn});
            var5.printStackTrace();
            logger.debug("getMQChannel1Debug\tconn:[%s]", new Object[]{mqConn});
            try {
                logger.debug("getMQChannelSecondStart");
                MQconnection = MQfactory.newConnection();
                mqChannel = MQconnection.createChannel();
                logger.debug("getMQChannelFirstEnd\tmqChannel:" + mqChannel + "\tMQconnection:" + MQconnection);
            } catch (Exception var4) {
                var4.printStackTrace();
               }
        }
        return mqChannel;
    }
    public Channel getMQChannelNew(Connection mqConn) {
        Channel mqChannel = null;
        try {
            logger.debug("getMQChannelFirstStart");
            mqChannel = mqConn.createChannel();
            if (mqChannel == null) {
                logger.debug("getMQChannelFirstNull\t" + MQconnection);
                if (MQconnection != null) {
                    MQconnection.close();
                }
                MQconnection = MQfactory.newConnection();
                mqChannel = MQconnection.createChannel();
            }
            logger.debug("getMQChannelFirstEnd\tmqChannel:" + mqChannel);
        } catch (Exception var6) {
            logger.warn("getMQChannel1Debug");
            logger.warn("getMQChannel1Debug\tconn:[%s]", new Object[]{mqConn});
            var6.printStackTrace();
            logger.debug("getMQChannel1Debug\tconn:[%s]", new Object[]{mqConn});
            try {
                logger.debug("getMQChannelSecondStart");
                MQconnection = MQfactory.newConnection();
                mqChannel = MQconnection.createChannel();
                logger.debug("getMQChannelFirstEnd\tmqChannel:" + mqChannel + "\tMQconnection:" + MQconnection);
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }
        return mqChannel;
    }
    public Connection getMQConn() {
        return MQconnection;
    }
    public RabbitMQConfig getConfig() {
        return this.config;
    }
    public void setConfig(RabbitMQConfig config) {
        this.config = config;
    }
}
