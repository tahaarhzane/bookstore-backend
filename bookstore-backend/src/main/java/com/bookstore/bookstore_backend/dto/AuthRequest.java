package com.bookstore.bookstore_backend.dto;

import lombok.Getter;
import lombok.Setter;
import com.bookstore.bookstore_backend.model.Role;

@Getter
@Setter
public class AuthRequest {
    private String email;
    private String password;
    private Role role; // Add role field
}

