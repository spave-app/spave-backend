package com.spave.backend.spave.service;

import com.spave.backend.spave.model.WaitlistEntry;
import com.spave.backend.spave.repository.WaitlistRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WaitlistService {
    private final WaitlistRepository waitlistRepository;

    public WaitlistService(WaitlistRepository waitlistRepository) {
        this.waitlistRepository = waitlistRepository;
    }

    public UUID addToWaitlist(String email) {
        if (waitlistRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already on the waitlist");
        }
        WaitlistEntry entry = new WaitlistEntry();
        entry.setEmail(email);
        return waitlistRepository.save(entry).getId();
    }

    public void removeFromWaitlist(UUID token) {
        if (!waitlistRepository.existsById(token)) {
            throw new IllegalArgumentException("Invalid unsubscribe token");
        }
        waitlistRepository.deleteById(token);
    }

    public long getWaitListSize() {
        return waitlistRepository.count();
    }
}
