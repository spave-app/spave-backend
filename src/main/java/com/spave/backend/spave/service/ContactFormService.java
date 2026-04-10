package com.spave.backend.spave.service;

import com.spave.backend.spave.model.ContactForm;
import com.spave.backend.spave.repository.ContactFormRepository;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class ContactFormService {

    private final ContactFormRepository contactFormRepository;
    private final MailService mailService;

    public ContactFormService(ContactFormRepository contactFormRepository, MailService mailService) {
        this.contactFormRepository = contactFormRepository;
        this.mailService = mailService;
    }

    public void addToContactForm(ContactForm contactForm) throws MessagingException, UnsupportedEncodingException {
        contactFormRepository.save(contactForm);
        mailService.sendContactFormNotification(
                contactForm.getName(),
                contactForm.getEmail(),
                contactForm.getSubject(),
                contactForm.getMessage()
        );
    }
}
