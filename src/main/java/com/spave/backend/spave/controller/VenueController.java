package com.spave.backend.spave.controller;

import com.spave.backend.spave.model.SoccerCourt;
import com.spave.backend.spave.model.Venue;
import com.spave.backend.spave.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/venues")
    public ResponseEntity<List<Venue>> getAllVenues() {
        return new ResponseEntity<>(venueService.getAllVenues(), HttpStatus.OK);
    }

    @GetMapping("/venues/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable UUID id) {
        return venueService.getVenueById(id)
                .map(venue -> new ResponseEntity<>(venue, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/venues/{id}/courts")
    public ResponseEntity<List<SoccerCourt>> getCourtsByVenueId(@PathVariable UUID id) {
        return new ResponseEntity<>(venueService.getCourtsByVenueId(id), HttpStatus.OK);
    }
}
