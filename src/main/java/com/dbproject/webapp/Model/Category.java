package com.dbproject.webapp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

// Category.java
@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;
    // getters and setters
}