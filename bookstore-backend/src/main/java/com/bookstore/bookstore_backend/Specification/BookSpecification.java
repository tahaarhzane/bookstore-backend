package com.bookstore.bookstore_backend.Specification;

import com.bookstore.bookstore_backend.model.Book;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BookSpecification {

    public static Specification<Book> filterBy(String title, String author, String category, Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            Specification<Book> spec = Specification.where(null);

            if (StringUtils.hasText(title)) {
                spec = spec.and((root1, query1, cb) -> cb.like(cb.lower(root1.get("title")), "%" + title.toLowerCase() + "%"));
            }
            if (StringUtils.hasText(author)) {
                spec = spec.and((root1, query1, cb) -> cb.like(cb.lower(root1.get("author")), "%" + author.toLowerCase() + "%"));
            }
            if (StringUtils.hasText(category)) {
                spec = spec.and((root1, query1, cb) -> cb.equal(root1.get("category"), category));
            }
            if (minPrice != null && maxPrice != null) {
                spec = spec.and((root1, query1, cb) -> cb.between(root1.get("price"), minPrice, maxPrice));
            }

            return spec.toPredicate(root, query, criteriaBuilder);
        };
    }
}

