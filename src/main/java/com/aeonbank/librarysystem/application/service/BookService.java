package com.aeonbank.librarysystem.application.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.aeonbank.librarysystem.domain.model.Book;
import com.aeonbank.librarysystem.domain.repository.BookRepository;
import com.aeonbank.librarysystem.domain.repository.specification.BookSpecifications;
import com.aeonbank.librarysystem.exception.InvalidBookException;
import com.aeonbank.librarysystem.utils.IsbnUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	public Book registerBook(String isbn, String title, String author) {

		String normalizedIsbn = IsbnUtils.normalize(isbn);
		
		Book existingBook = bookRepository.findFirstByIsbn(normalizedIsbn);
		if (existingBook != null) {
			if (!existingBook.getTitle().equalsIgnoreCase(title)
					|| !existingBook.getAuthor().equalsIgnoreCase(author)) {
				throw new InvalidBookException(normalizedIsbn, title, author);
			}
		}
		return bookRepository.save(new Book(normalizedIsbn, title, author));
	}

	public Page<Book> getAllBooks(String isbn, String title, String author, Pageable pageable) {

		Specification<Book> spec = BookSpecifications.withFilters(isbn, title, author);
		return bookRepository.findAll(spec, pageable);
	}
}
