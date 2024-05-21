package com.dbproject.webapp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

// Role.java
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // getters and setters
}
