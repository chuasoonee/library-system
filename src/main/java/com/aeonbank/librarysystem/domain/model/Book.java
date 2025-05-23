package com.aeonbank.librarysystem.domain.model;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.ISBN.Type;

import com.aeonbank.librarysystem.exception.InvalidIsbnException;
import com.aeonbank.librarysystem.utils.IsbnUtils;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Book extends BaseModel {

	@ISBN(type = Type.ANY, message = "{isbn.invalid}")
	@NotBlank
	private String isbn;

	@NotBlank
	private String title;

	@NotBlank
	private String author;

	public Book(String isbn, String title, String author) {

		this.isbn = normalizedIsbn(isbn);
		this.title = title;
		this.author = author;
	}

	private String normalizedIsbn(String isbn) {

		String normalizedIsbn = IsbnUtils.normalize(isbn);
		if (!IsbnUtils.isValidIsbn(normalizedIsbn)) {
			throw new InvalidIsbnException(normalizedIsbn);
		}
		return normalizedIsbn;
	}
}