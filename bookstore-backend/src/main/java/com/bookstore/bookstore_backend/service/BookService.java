package com.bookstore.bookstore_backend.service;

import com.bookstore.bookstore_backend.Specification.BookSpecification;
import com.bookstore.bookstore_backend.model.Book;
import com.bookstore.bookstore_backend.repository.BookRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }


    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String title, String author, String category, Double minPrice, Double maxPrice) {
        Specification<Book> spec = BookSpecification.filterBy(title, author, category, minPrice, maxPrice);
        return bookRepository.findAll(spec);
    }

    public Page<Book> getPaginatedBooks(Pageable pageable) {
        System.out.println("Page Number: " + pageable.getPageNumber());
        System.out.println("Page Size: " + pageable.getPageSize());
        return bookRepository.findAll(pageable);
    }


    public Page<Book> searchBooksPaginated(String title, String author, String category, Double minPrice, Double maxPrice, Pageable pageable) {
        Specification<Book> spec = BookSpecification.filterBy(title, author, category, minPrice, maxPrice);
        return bookRepository.findAll(spec, pageable);
    }
}
