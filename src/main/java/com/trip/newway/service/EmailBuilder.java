package com.trip.newway.service;

import javax.mail.internet.MimeMessage;

public interface EmailBuilder<T> {

    MimeMessage buildMessageWithAttachments(T entity);
}
