package com.spave.backend.spave.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {
    private static final String FROM_ADDRESS = "contact@spaveapp.com";
    private static final String FROM_NAME = "Spave";

    private final JavaMailSender mailSender;


    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendWaitlistConfirmation(String to) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(FROM_ADDRESS, FROM_NAME);
        helper.setTo(to);
        helper.setSubject("You're on the Spave waitlist");
        helper.setText(loadTemplate(), true);

        mailSender.send(message);
    }

    private String loadTemplate() throws IOException {
        try (var in = new ClassPathResource("templates/emails/waitlist-confirmation.html").getInputStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
