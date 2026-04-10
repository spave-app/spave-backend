package com.spave.backend.spave.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CourtLocationDTO {
    private UUID courtId;
    private UUID venueId;
    private String venueName;
    private Double lat;
    private Double lng;

    public CourtLocationDTO(UUID courtId, UUID venueId, String venueName, Double lat, Double lng) {
        this.courtId = courtId;
        this.venueId = venueId;
        this.venueName = venueName;
        this.lat = lat;
        this.lng = lng;
    }
}
