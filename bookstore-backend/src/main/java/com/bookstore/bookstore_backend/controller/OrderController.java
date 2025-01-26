package com.bookstore.bookstore_backend.controller;

import com.bookstore.bookstore_backend.model.Order;
import com.bookstore.bookstore_backend.service.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public Order checkout(@AuthenticationPrincipal UserDetails userDetails) {
        return orderService.checkoutCart(userDetails.getUsername());
    }

    @GetMapping("/history")
    public List<Order> getOrderHistory(@AuthenticationPrincipal UserDetails userDetails) {
        return orderService.getUserOrders(userDetails.getUsername());
    }
}
