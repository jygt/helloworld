package com.example.helloWorld.activemq;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class AyMoodProducer {

    @Resource
    private JmsMessagingTemplate objJmsMessagingTemplate;

    public void sendMessage(Destination dest, final String msg){
        objJmsMessagingTemplate.convertAndSend( dest,msg);
    }
    public void sendMessage(Destination dest,final AyMood ayMood){
        objJmsMessagingTemplate.convertAndSend(dest,ayMood);
    }

}
