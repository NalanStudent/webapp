package com.dbproject.webapp.Repository;

import com.dbproject.webapp.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
