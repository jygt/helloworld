package com.example.helloWorld.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class TUrlListener implements ServletContextListener {

    // 使用log4j
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());


    @Override
    public void contextInitialized(ServletContextEvent sce){

        //System.out.println("ServletContext init ...");

        logger.info("log4j2 contextInitialized init ... ");
    }
    @Override
    public  void contextDestroyed(ServletContextEvent sce){
        //System.out.println("ServletContext Destroyed ...");
        logger.info("log4j2 contextDestroyed destroy ... ");
    }
}
