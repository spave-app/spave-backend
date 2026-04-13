package com.spave.backend.spave.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false, unique = true)
    @ToString.Exclude
    private Venue venue;

    @NotBlank
    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @NotBlank
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9\\s-]+$")
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "neighborhood")
    private String neighborhood;

    @NotNull
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    @Column(name = "lat", nullable = false)
    private Double lat;

    @NotNull
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    @Column(name = "lng", nullable = false)
    private Double lng;

    @Column(name = "is_primary", nullable = false)
    private boolean isPrimary = true;

    @NotBlank
    @Column(name = "province", nullable = false)
    private String province;

    @NotBlank
    @Column(name = "country", nullable = false)
    private String country;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
