package com.bookstore.bookstore_backend.service;

import com.bookstore.bookstore_backend.model.Book;
import com.bookstore.bookstore_backend.model.Cart;
import com.bookstore.bookstore_backend.model.CartItem;
import com.bookstore.bookstore_backend.repository.BookRepository;
import com.bookstore.bookstore_backend.repository.CartItemRepository;
import com.bookstore.bookstore_backend.repository.CartRepository;
import org.springframework.stereotype.Service;
import com.bookstore.bookstore_backend.model.User;
import com.bookstore.bookstore_backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, CartItemRepository cartItemRepository, BookRepository bookRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
    }

    public double calculateTotalPrice(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();
    }




    public Cart getCartByUser(String username) {
        Cart cart = cartRepository.findByUserEmail(username);
        if (cart == null) {
            throw new RuntimeException("Cart not found");
        }
        cart.setTotalPrice(calculateTotalPrice(cart));
        return cart;
    }


    public Cart getOrCreateCart(User user) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());  // Ensure initialization here
            cart.setTotalPrice(0);
            cartRepository.save(cart);
        }
        return cart;
    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
    public Cart addToCart(String email, Long bookId, int quantity) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());
        }

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        CartItem cartItem = new CartItem(cart, book, quantity, book.getPrice() * quantity);

        cart.getItems().add(cartItem);
        cart.setTotalPrice(cart.getTotalPrice() + cartItem.getPrice());

        return cartRepository.save(cart);
    }

}

