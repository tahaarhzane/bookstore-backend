package com.bookstore.bookstore_backend.repository;

import com.bookstore.bookstore_backend.model.Cart;
import com.bookstore.bookstore_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserEmail(String email);

    Cart findByUser(User user);
}

