package com.bookstore.bookstore_backend.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @Positive(message = "Price must be greater than zero")
    private double price;

    @Column(nullable = false)
    private String category;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

}

