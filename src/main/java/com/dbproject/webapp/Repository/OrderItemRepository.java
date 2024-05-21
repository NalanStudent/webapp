package com.dbproject.webapp.Repository;

import com.dbproject.webapp.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { }