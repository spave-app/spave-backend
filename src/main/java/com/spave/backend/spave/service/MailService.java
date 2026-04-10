package com.spave.backend.spave.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
public class MailService {
    private static final String FROM_ADDRESS = "contact@spaveapp.com";
    private static final String FROM_NAME = "Spave";

    private final JavaMailSender mailSender;


    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendContactFormNotification(String fromName, String fromEmail, String subject, String messageBody) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(FROM_ADDRESS, FROM_NAME);
        helper.setTo(FROM_ADDRESS);
        helper.setSubject("Client contacted you");
        helper.setText(
                "<p><strong>From:</strong> " + fromName + " &lt;" + fromEmail + "&gt;</p>" +
                "<p><strong>Subject:</strong> " + subject + "</p>" +
                "<p><strong>Message:</strong></p>" +
                "<p>" + messageBody + "</p>",
                true
        );
        mailSender.send(message);
    }

    public void sendWaitlistConfirmation(String to, String unsubscribeUrl) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(FROM_ADDRESS, FROM_NAME);
        helper.setTo(to);
        helper.setSubject("You're on the Spave waitlist");
        helper.setText(loadTemplate(unsubscribeUrl), true);

        mailSender.send(message);
    }

    private String loadTemplate(String unsubscribeUrl) throws IOException {
        try (var in = new ClassPathResource("templates/emails/waitlist-confirmation.html").getInputStream()) {
            String html = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            return html.replace("{{UNSUBSCRIBE_URL}}", unsubscribeUrl);
        }
    }
}
