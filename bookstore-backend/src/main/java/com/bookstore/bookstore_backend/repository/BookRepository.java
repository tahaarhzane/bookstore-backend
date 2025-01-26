package com.bookstore.bookstore_backend.repository;

import com.bookstore.bookstore_backend.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll(Specification<Book> spec);
    Page<Book> findAll(Pageable pageable);


    Page<Book> findAll(Specification<Book> spec, Pageable pageable);

    // Search by category using a custom query
    @Query("SELECT b FROM Book b WHERE b.category = :category")
    List<Book> findByCategory(String category);
}

