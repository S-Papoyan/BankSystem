package com.digi.banksystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private MailSender mailSender;

    @Async
    public void sendEmail(String to, String subject, String text) {


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mher.kalashyan888@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);

    }

}
