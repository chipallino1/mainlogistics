package com.samsolutions.logistics.mainlogistics.services.utils.impl;

import com.samsolutions.logistics.mainlogistics.services.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderImpl implements MailSender {

    private JavaMailSender javaMailSender;

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(String dest, String subject, String content) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();

        simpleMailMessage.setFrom("");
        simpleMailMessage.setTo(dest);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        javaMailSender.send(simpleMailMessage);
    }
}
