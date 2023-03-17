package com.example.smmspace.services.Impl;

import com.example.smmspace.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class EmailServiceImpl implements EmailService {
    private static Logger logger = Logger.getLogger(EmailServiceImpl.class.getName());

    private static final String NOREPLY_ADDRESS = "bukachvladislav@gmail.com";

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            logger.info("All it`s good");
    }
}