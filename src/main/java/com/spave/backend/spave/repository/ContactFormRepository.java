package com.spave.backend.spave.repository;

import com.spave.backend.spave.model.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactFormRepository extends JpaRepository<ContactForm, UUID> {
}
