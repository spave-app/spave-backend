package com.spave.backend.spave.controller;

import com.spave.backend.spave.model.ContactForm;
import com.spave.backend.spave.service.ContactFormService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactFormController {
    private final ContactFormService contactFormService;

    public ContactFormController(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }

    @PostMapping
    public ResponseEntity<String> addToContactForm(@Valid @RequestBody ContactForm contactForm) {
        try {
            contactFormService.addToContactForm(contactForm);
            return new ResponseEntity<>("Successfully added the customer's email", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred when adding to contact form: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
