package com.spave.backend.spave.service;

import com.spave.backend.spave.model.SoccerCourt;
import com.spave.backend.spave.repository.SoccerCourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SoccerCourtService {
    private final SoccerCourtRepository soccerCourtRepository;

    @Autowired
    public SoccerCourtService(SoccerCourtRepository soccerCourtRepository) {
        this.soccerCourtRepository = soccerCourtRepository;
    }

    public List<SoccerCourt> getAllActiveCourts() {
        return soccerCourtRepository.findByIsActiveTrue();
    }

    public Optional<SoccerCourt> getCourtById(UUID id) {
        return soccerCourtRepository.findById(id);
    }
}
