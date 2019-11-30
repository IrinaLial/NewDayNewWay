package com.trip.newway.service.impl.email;

import com.trip.newway.service.EmailBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@PropertySource("classpath:email.properties")
abstract public class EmailBuilderImpl<T> implements EmailBuilder<T> {

    @Value("${mail.from}")
    private String sender;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public MimeMessage buildMessageWithAttachments(T entity) {
        final MimeMessage message = javaMailSender.createMimeMessage();
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            message.setContent(getMessageBody(entity), "text/html; charset=UTF-8");
            helper.setSubject(this.getMessageSubject());
            helper.setFrom(sender);
            helper.setTo(this.getMessageRecipient(entity));
            return message;
        } catch (MessagingException e) {
            return null;
        }
    }

    abstract String getMessageRecipient(T entity);

    abstract String getMessageBody(T entity);

    abstract String getMessageSubject();


}
