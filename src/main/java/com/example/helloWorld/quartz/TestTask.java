package com.example.helloWorld.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestTask {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(TestTask.class);

    public void run(){
        logger.debug("TestTask Timer was launched at here!  ");
    }
}
