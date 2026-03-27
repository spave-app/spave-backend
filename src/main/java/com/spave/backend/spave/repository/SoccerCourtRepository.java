package com.spave.backend.spave.repository;

import com.spave.backend.spave.model.SoccerCourt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SoccerCourtRepository extends JpaRepository<SoccerCourt, UUID> {
    List<SoccerCourt> findByVenueId(UUID venueId);

    List<SoccerCourt> findByIsActiveTrue();
}
