package com.trip.newway.service;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendMessage(MimeMessage mimeMessage);
}
