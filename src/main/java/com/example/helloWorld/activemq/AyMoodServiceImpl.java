package com.example.helloWorld.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class AyMoodServiceImpl implements AyMoodService {
    @Resource
    private AyMoodRepository objAyMoodRepository;

    @Override
    public AyMood save(AyMood objAyMood){
        return objAyMoodRepository.save(objAyMood);
    }

    // activemq
    private static Destination objDestination = new ActiveMQQueue("ay.queue.asyn.save");

    @Resource
    private AyMoodProducer objAyMoodProducer;
    @Override
    public String asynSave(AyMood objAyMood){
        objAyMoodProducer.sendMessage(objDestination,objAyMood);
        return "success";
    }
}
