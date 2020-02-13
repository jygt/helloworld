package com.example.helloWorld.mail.impl;

import com.example.helloWorld.TUrl;
import com.example.helloWorld.TUrlService;
import com.example.helloWorld.mail.SendJunkMailService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service

public class SendJunkMailServiceImpl implements SendJunkMailService {
    // JavaMailSender 邮件发送接口，在spring boot的starter模块已经提供了自动化配置，@Autowired注入进来即可使用
    @Autowired
    JavaMailSender mailSender;
    @Resource
    private TUrlService objTUrlService;

    // 可将application.properties配置文件中的配置设置到属性中
    @Value("${spring.mail.username}")
    private String from;
    public static final Logger logger = LoggerFactory.getLogger(SendJunkMailServiceImpl.class);

    @Override
    public boolean sendJunkMail(List<TUrl> urlList){
        try{
            if(urlList == null || urlList.size() <= 0)
                return Boolean.FALSE;

            MimeMessage objMimeMessage = this.mailSender.createMimeMessage();

            MimeMessageHelper msg = new MimeMessageHelper(objMimeMessage);

            msg.setFrom(from);
            msg.setSubject("测试TEst");
            msg.setTo("7457222@qq.com");

            StringBuffer sBffer = new StringBuffer();
            for(TUrl url:urlList){

                sBffer.append(url.getFName() + ":" +url.getF_url()+"\n");


            }
            msg.setText(sBffer.toString());
            //this.mailSender.send(objMimeMessage);
        }
        catch (Exception exp){
            logger.error("sendJunkMail error and url = %s",urlList,exp);
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }
}
