package com.spave.backend.spave.controller;

import com.spave.backend.spave.dto.WaitlistRequest;
import com.spave.backend.spave.service.WaitlistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/waitlist")
public class WaitlistController {
    private final WaitlistService waitlistService;

    public WaitlistController(WaitlistService waitlistService) {
        this.waitlistService = waitlistService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> joinWaitlist(@Valid @RequestBody WaitlistRequest request) {
        try {
            waitlistService.addToWaitlist(request.getEmail());
            return new ResponseEntity<>(Map.of("message", "Successfully joined the waitlist"), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("message", e.getMessage()), HttpStatus.CONFLICT);
        }
    }
}
