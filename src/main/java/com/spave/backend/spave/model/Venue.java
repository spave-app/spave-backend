package com.spave.backend.spave.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;
import java.time.Instant;

@Entity
@Table(name = "venues")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Pattern(regexp = "^\\+[1-9]\\d{7,14}$")
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank
    @URL
    @Column(name = "website", nullable = false)
    private String website;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "source", nullable = true)
    private String source;

    @Column(name = "created_at", insertable = false, updatable = false, nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", insertable = false, nullable = false)
    private Instant updatedAt;

    @Email
    @Column(name = "email", nullable = true)
    private String email;

    @Pattern(regexp = "^\\d+$")
    @Column(name = "phone_extension", nullable = true)
    private String phoneExtension;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;


}
