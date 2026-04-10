package com.spave.backend.spave.controller;

import com.spave.backend.spave.dto.CourtLocationDTO;
import com.spave.backend.spave.model.SoccerCourt;
import com.spave.backend.spave.service.SoccerCourtService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class SoccerCourtController {
    private final SoccerCourtService soccerCourtService;

    public SoccerCourtController(SoccerCourtService soccerCourtService) {
        this.soccerCourtService = soccerCourtService;
    }

    @GetMapping("/courts")
    public ResponseEntity<@NonNull List<SoccerCourt>> getAllActiveCourts() {
        return new ResponseEntity<>(soccerCourtService.getAllActiveCourts(), HttpStatus.OK);
    }

    @GetMapping("/courts/{id}")
    public ResponseEntity<SoccerCourt> getCourtById(@PathVariable UUID id) {
        return soccerCourtService.getCourtById(id)
                .map(court -> new ResponseEntity<>(court, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/courts/locations")
    public ResponseEntity<List<CourtLocationDTO>> getCourtLocations() {
        return new ResponseEntity<>(soccerCourtService.getCourtLocations(), HttpStatus.OK);
    }
}
