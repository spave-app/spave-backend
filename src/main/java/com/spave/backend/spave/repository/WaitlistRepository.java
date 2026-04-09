package com.spave.backend.spave.repository;

import com.spave.backend.spave.model.WaitlistEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WaitlistRepository extends JpaRepository<WaitlistEntry, UUID> {
    boolean existsByEmail(String email);
}
