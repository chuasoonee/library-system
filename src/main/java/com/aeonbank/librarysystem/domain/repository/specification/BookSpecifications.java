package com.aeonbank.librarysystem.domain.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import com.aeonbank.librarysystem.domain.model.Book;

public class BookSpecifications {

	public static Specification<Book> withFilters(String isbn, String title, String author) {
		return (root, query, cb) -> {

			Specification<Book> spec = Specification.where(null);
			
			if (title != null && !title.isEmpty()) {
				spec = spec.and(titleContains(title));
			}
			if (author != null && !author.isEmpty()) {
				spec = spec.and(authorContains(author));
			}
			if (isbn != null && !isbn.isEmpty()) {
				spec = spec.and(isbnEqual(isbn));
			}
			return spec.toPredicate(root, query, cb);
		};
	}

	private static Specification<Book> titleContains(String title) {
		return (root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
	}

	private static Specification<Book> authorContains(String author) {
		return (root, query, cb) -> cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%");
	}

	private static Specification<Book> isbnEqual(String isbn) {
		return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
	}
}