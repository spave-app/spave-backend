package com.spave.backend.spave.service;

import com.spave.backend.spave.model.WaitlistEntry;
import com.spave.backend.spave.repository.WaitlistRepository;
import org.springframework.stereotype.Service;

@Service
public class WaitlistService {
    private final WaitlistRepository waitlistRepository;

    public WaitlistService(WaitlistRepository waitlistRepository) {
        this.waitlistRepository = waitlistRepository;
    }

    public WaitlistEntry addToWaitlist(String email) {
        if (waitlistRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already on the waitlist");
        }
        WaitlistEntry entry = new WaitlistEntry();
        entry.setEmail(email);
        return waitlistRepository.save(entry);
    }
}
