package com.bookstore.bookstore_backend.repository;

import com.bookstore.bookstore_backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
