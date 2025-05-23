package com.aeonbank.librarysystem.interfaces.rest.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aeonbank.librarysystem.application.service.LoanService;
import com.aeonbank.librarysystem.domain.model.Loan;
import com.aeonbank.librarysystem.domain.model.LoanStatus;
import com.aeonbank.librarysystem.interfaces.rest.dto.CreatedLoanRequestDTO;
import com.aeonbank.librarysystem.interfaces.rest.dto.GetLoanResponseDTO;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/loans")
public class LoanController {

	private final LoanService loanService;

	@PostMapping
	public ResponseEntity<Void> borrowBook(@RequestBody @Valid CreatedLoanRequestDTO request) {

		Loan loan = loanService.loanBook(request.borrowerId(), request.bookId());
		return ResponseEntity.created(URI.create("/loans/" + loan.getId())).build();
	}

	@PatchMapping("/{loanId}")
	public ResponseEntity<Void> returnBook(@PathVariable Long loanId) {

		loanService.returnBook(loanId);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Get all loans list API
	 * 
	 * @param borrowerName
	 * @param borrowerEmail
	 * @param loanStatus
	 * @param pageable
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Page<GetLoanResponseDTO>> getAllLoans(@RequestParam(required = false) String borrowerName,
			@RequestParam(required = false) String borrowerEmail, @RequestParam(required = false) LoanStatus loanStatus,
			@PageableDefault Pageable pageable) {

		return new ResponseEntity<>(loanService.getAllLoans(borrowerName, borrowerEmail, loanStatus, pageable)
				.map(loan -> new GetLoanResponseDTO(loan.getId(), loan.getBook().getId(), loan.getBook().getTitle(),
						loan.getBook().getIsbn(), loan.getBorrower().getId(), loan.getBorrower().getName(),
						loan.getBorrower().getEmail(), loan.getLoanDate(), loan.getReturnedDate())),
				HttpStatus.OK);
	}
}
