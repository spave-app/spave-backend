package com.spave.backend.spave.controller;

import com.spave.backend.spave.dto.WaitlistRequest;
import com.spave.backend.spave.service.MailService;
import com.spave.backend.spave.service.WaitlistService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/waitlist")
public class WaitlistController {
    private final WaitlistService waitlistService;
    private final MailService mailService;

    @Value("${app.base-url}")
    private String baseUrl;

    public WaitlistController(WaitlistService waitlistService, MailService mailService) {
        this.waitlistService = waitlistService;
        this.mailService = mailService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> joinWaitlist(@Valid @RequestBody WaitlistRequest request) {
        try {
            UUID token = waitlistService.addToWaitlist(request.getEmail());
            String unsubscribeUrl = baseUrl + "/waitlist/unsubscribe?token=" + token;
            mailService.sendWaitlistConfirmation(request.getEmail(), unsubscribeUrl);
            return new ResponseEntity<>(Map.of("message", "Successfully joined the waitlist"), HttpStatus.CREATED);
        } catch (IllegalArgumentException | IOException e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.CONFLICT);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/unsubscribe")
    public ResponseEntity<@NonNull Map<String, String>> unsubscribe(@RequestParam UUID token) {
        try {
            waitlistService.removeFromWaitlist(token);
            return new ResponseEntity<>(Map.of("message", "You have been removed from the waitlist"), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/size")
    public ResponseEntity<@NonNull String> getWaitListSize() {
        try {
            long size = waitlistService.getWaitListSize();

            return new ResponseEntity<>(String.valueOf(size), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception occured when retrieving waitlist size: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
