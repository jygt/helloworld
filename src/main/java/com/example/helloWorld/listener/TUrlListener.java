package com.example.helloWorld.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TUrlListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        System.out.println("ServletContext init ...");
    }
    @Override
    public  void contextDestroyed(ServletContextEvent sce){
        System.out.println("ServletContext Destroyed ...");
    }
}
