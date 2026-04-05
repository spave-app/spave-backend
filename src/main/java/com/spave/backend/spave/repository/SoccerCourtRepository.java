package com.spave.backend.spave.repository;

import com.spave.backend.spave.dto.CourtLocationDTO;
import com.spave.backend.spave.model.SoccerCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SoccerCourtRepository extends JpaRepository<SoccerCourt, UUID> {
    List<SoccerCourt> findByVenueId(UUID venueId);

    List<SoccerCourt> findByIsActiveTrue();

    @Query("SELECT new com.spave.backend.spave.dto.CourtLocationDTO(c.id, v.id, v.name, a.lat, a.lng) " +
           "FROM SoccerCourt c JOIN c.venue v JOIN v.address a WHERE c.isActive = true")
    List<CourtLocationDTO> findAllActiveCourtLocations();
}
