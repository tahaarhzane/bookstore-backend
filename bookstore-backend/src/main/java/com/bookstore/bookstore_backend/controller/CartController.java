package com.bookstore.bookstore_backend.controller;

import com.bookstore.bookstore_backend.model.Book;
import com.bookstore.bookstore_backend.model.Cart;
import com.bookstore.bookstore_backend.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @GetMapping("/user")
    public ResponseEntity<Cart> getCart(Authentication authentication) {
        String email = authentication.getName();
        Cart cart = cartService.getCartByUser(email);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(Authentication authentication,
                                          @RequestParam Long bookId,
                                          @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addToCart(authentication.getName(), bookId, quantity));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}


