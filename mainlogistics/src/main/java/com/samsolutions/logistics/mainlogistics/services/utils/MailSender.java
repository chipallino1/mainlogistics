package com.samsolutions.logistics.mainlogistics.services.utils;

import org.springframework.stereotype.Component;

@Component
public interface MailSender {
    void sendMail(String dest,String subject,String content);
}
