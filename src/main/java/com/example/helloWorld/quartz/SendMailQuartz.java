package com.example.helloWorld.quartz;

import com.example.helloWorld.TUrl;
import com.example.helloWorld.TUrlService;
import com.example.helloWorld.mail.SendJunkMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Configurable
@EnableScheduling

public class SendMailQuartz {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(SendMailQuartz.class);

    @Resource
    private SendJunkMailService objSendJunkMailService;

    @Resource
    private TUrlService objTUrlService;

    @Scheduled(cron = "* * */20 * * * ")
    public void reportCurrentByCron(){
        List<TUrl> urlList = objTUrlService.findAll();
        if(urlList == null  || urlList.size() <= 0)
            return;
        objSendJunkMailService.sendJunkMail(urlList);
        logger.info("SendMailQuartz Timer was launched at here!  ");
    }
}
