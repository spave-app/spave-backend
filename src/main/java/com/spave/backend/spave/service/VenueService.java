package com.spave.backend.spave.service;

import com.spave.backend.spave.model.SoccerCourt;
import com.spave.backend.spave.model.Venue;
import com.spave.backend.spave.repository.SoccerCourtRepository;
import com.spave.backend.spave.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VenueService {
    private final VenueRepository venueRepository;
    private final SoccerCourtRepository soccerCourtRepository;

    @Autowired
    public VenueService(VenueRepository venueRepository, SoccerCourtRepository soccerCourtRepository) {
        this.venueRepository = venueRepository;
        this.soccerCourtRepository = soccerCourtRepository;
    }

    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    public Optional<Venue> getVenueById(UUID id) {
        return venueRepository.findById(id);
    }

    public List<SoccerCourt> getCourtsByVenueId(UUID venueId) {
        return soccerCourtRepository.findByVenueId(venueId);
    }
}
