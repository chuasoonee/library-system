package com.aeonbank.librarysystem.interfaces.rest.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aeonbank.librarysystem.application.service.BookService;
import com.aeonbank.librarysystem.domain.model.Book;
import com.aeonbank.librarysystem.interfaces.rest.dto.GetBookResponseDTO;
import com.aeonbank.librarysystem.interfaces.rest.dto.RegisterBookRequestDTO;
import com.aeonbank.librarysystem.interfaces.rest.dto.RegisterBookResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@PostMapping("/books")
	public ResponseEntity<RegisterBookResponseDTO> registerBook(@RequestBody @Valid RegisterBookRequestDTO request) {

		Book book = bookService.registerBook(request.isbn(), request.title(), request.author());
		return ResponseEntity.created(URI.create("/books/" + book.getId()))
				.body(new RegisterBookResponseDTO(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor()));
	}

	@GetMapping("/books")
	public ResponseEntity<Page<GetBookResponseDTO>> getBooks(@RequestParam(required = false) String isbn,
			@RequestParam(required = false) String title, @RequestParam(required = false) String author,
			@PageableDefault Pageable pageable) {

		return new ResponseEntity<>(bookService.getAllBooks(isbn, title, author, pageable)
				.map(book -> new GetBookResponseDTO(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor())),
				HttpStatus.OK);
	}
}