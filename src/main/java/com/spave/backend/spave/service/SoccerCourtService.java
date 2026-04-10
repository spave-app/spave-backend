package com.spave.backend.spave.service;

import com.spave.backend.spave.dto.CourtLocationDTO;
import com.spave.backend.spave.model.SoccerCourt;
import com.spave.backend.spave.repository.SoccerCourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("courts")
    public List<SoccerCourt> getAllActiveCourts() {
        return soccerCourtRepository.findByIsActiveTrue();
    }

    public Optional<SoccerCourt> getCourtById(UUID id) {
        return soccerCourtRepository.findById(id);
    }

    public List<CourtLocationDTO> getCourtLocations() {
        return soccerCourtRepository.findAllActiveCourtLocations();
    }
}
