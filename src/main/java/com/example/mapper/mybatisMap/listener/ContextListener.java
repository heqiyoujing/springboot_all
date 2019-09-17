package com.example.mapper.mybatisMap.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author: yiqq
 * @date: 2018/8/8 20:35
 * @description:
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
        System.out.println("监听器被销毁");
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  {
        System.out.println("监听器初始化。");
    }

}
