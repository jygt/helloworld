package com.example.helloWorld.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AyMoodConsumer {

    // 使用log4j
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());


    @JmsListener(destination = "ay.queue")
    public void receiveQueue(String txt){
        //System.out.println("User deploy a note 消息发表["+txt+"]成功success!");
        logger.info("User deploy a note 消息发表["+txt+"]成功success!");
    }

    @Resource
    private AyMoodService objAyMoodService;

    @JmsListener(destination = "ay.queue.asyn.save")
    public void reveiveQueue(AyMood ayMood){
        objAyMoodService.save(ayMood);
    }
}
