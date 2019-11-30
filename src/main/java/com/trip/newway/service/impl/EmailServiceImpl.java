package com.trip.newway.service.impl;

import com.trip.newway.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMessage(MimeMessage mimeMessage) {
        if (mimeMessage == null) {
            throw new NullPointerException("Message is null");
        }
        try {
            log.info("message sent to " + Arrays.toString(mimeMessage.getAllRecipients()));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
