package com.dbproject.webapp.Repository;


import com.dbproject.webapp.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> { }