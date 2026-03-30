package com.spave.backend.spave.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.spave.backend.spave.enums.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "soccer_courts")
public class SoccerCourt {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    @ToString.Exclude
    private Venue venue;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private SoccerCourtSize size;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SoccerCourtType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "surface", nullable = false)
    private SoccerCourtSurface surface;

    @NotBlank
    @URL
    @Column(name = "booking_link", nullable = false)
    private String bookingLink;

    @Column(name = "availability_hint", nullable = false)
    private List<String> availabilityHint;

    @Column(name = "notes")
    private String notes;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @NotNull
    @Min(0)
    @Column(name = "number_available", nullable = false)
    private short numberAvailable;

    @DecimalMin(value = "0.0")
    @Digits(integer = 8, fraction = 2)
    @Column(name = "price_min")
    private BigDecimal priceMin;

    @DecimalMin(value = "0.0")
    @Digits(integer = 8, fraction = 2)
    @Column(name = "price_max")
    private BigDecimal priceMax;

    @Column(name = "image_url")
    private String imageUrl;
}
