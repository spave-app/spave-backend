package com.spave.backend.spave.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "`User`")
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String roles;

}
