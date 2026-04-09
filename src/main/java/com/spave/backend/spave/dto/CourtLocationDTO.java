package com.spave.backend.spave.dto;

import java.util.UUID;

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

    public UUID getCourtId() { return courtId; }
    public UUID getVenueId() { return venueId; }
    public String getVenueName() { return venueName; }
    public Double getLat() { return lat; }
    public Double getLng() { return lng; }
}
