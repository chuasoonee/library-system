package com.aeonbank.librarysystem.application.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.aeonbank.librarysystem.domain.model.Book;
import com.aeonbank.librarysystem.domain.model.Borrower;
import com.aeonbank.librarysystem.domain.model.Loan;
import com.aeonbank.librarysystem.domain.repository.BookRepository;
import com.aeonbank.librarysystem.domain.repository.BorrowerRepository;
import com.aeonbank.librarysystem.domain.repository.LoanRepository;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

//	@InjectMocks
//	private LoanService loanService;
//
//	@Mock
//	private BookRepository bookRepository;
//
//	@Mock
//	private BorrowerRepository borrowerRepository;
//
//	@Mock
//	private LoanRepository loanRepository;
//
//	private Book testBook;
//	private Borrower testBorrower;
//
//	private final Long validBookId = 1L;
//	private final Long validBorrowerId = 1L;
//	private Loan activeLoan;
//	
//	@BeforeEach
//	void setUp() {
//		
//		testBook = bookRepository.save(new Book("978-3-16-148410-0", "Clean Code", "Robert Martin"));
//		testBorrower = borrowerRepository.save(new Borrower("John Doe", "john.doe@example.com"));
//	}
//	
//	
//
//	@Test
//	void borrowBook_Success() {
//		
//		when(bookRepository.findById(validBookId)).thenReturn(Optional.of(testBook));
//		when(borrowerRepository.findById(validBorrowerId)).thenReturn(Optional.of(testBorrower));
//		when(loanRepository.findByBookIdAndReturnedDateIsNull(validBookId)).thenReturn(Optional.empty());
//
//		Loan loan = loanService.loanBook(validBorrowerId, validBookId);
//		
//		assertNotNull(loan);
//		verify(loanRepository).save(any(Loan.class));
//	}
//
//	@Test
//	void borrowBook_BookAlreadyBorrowed() {
//		
//		when(bookRepository.findById(validBookId)).thenReturn(Optional.of(testBook));
//		when(loanRepository.findByBookIdAndReturnedDateIsNull(validBookId)).thenReturn(Optional.of(activeLoan));
//
//		assertThrows(BookAlreadyOnLoanException.class, () -> loanService.loanBook(validBorrowerId, validBookId));
//	}
//
//	@Test
//	void borrowBook_BorrowerNotFound() {
//		when(borrowerRepository.findById(validBorrowerId)).thenReturn(Optional.empty());
//
//		assertThrows(BorrowerNotFoundException.class, () -> loanService.loanBook(validBorrowerId, validBookId));
//	}
//
//	@Test
//	void borrowBook_BookNotFound() {
//		when(bookRepository.findById(validBookId)).thenReturn(Optional.empty());
//
//		assertThrows(BookNotFoundException.class, () -> loanService.loanBook(validBorrowerId, validBookId));
//	}
//
//	@Test
//	void returnBook_Success() {
//		
//		when(loanRepository.findByBookIdAndBorrowerIdAndReturnedDateIsNull(validBookId, validBorrowerId))
//				.thenReturn(Optional.of(activeLoan));
//
//		loanService.returnBook(validBorrowerId, validBookId);
//
//		assertNotNull(activeLoan.getReturnedDate());
//		verify(loanRepository).save(activeLoan);
//	}
//
//	@Test
//	void returnBook_LoanNotFound() {
//		when(loanRepository.findByBookIdAndBorrowerIdAndReturnedDateIsNull(validBookId, validBorrowerId))
//				.thenReturn(Optional.empty());
//
//		assertThrows(LoanNotFoundException.class, () -> loanService.returnBook(validBorrowerId, validBookId));
//	}
//
//	@Test
//	void returnBook_AlreadyReturned() {
//		activeLoan.setReturnedDate(LocalDateTime.now());
//		when(loanRepository.findByBookIdAndBorrowerIdAndReturnedDateIsNull(validBookId, validBorrowerId))
//				.thenReturn(Optional.empty());
//
//		assertThrows(LoanNotFoundException.class, () -> loanService.returnBook(validBorrowerId, validBookId));
//	}
//
//	@Test
//	void borrowBook_ConcurrentBorrowAttempt() {
//		when(bookRepository.findById(validBookId)).thenReturn(Optional.of(testBook));
//		when(borrowerRepository.findById(validBorrowerId)).thenReturn(Optional.of(testBorrower));
//		when(loanRepository.findByBookIdAndReturnedDateIsNull(validBookId)).thenReturn(Optional.empty());
//		when(loanRepository.save(any(Loan.class))).thenThrow(DataIntegrityViolationException.class);
//
//		assertThrows(BookAlreadyBorrowedException.class, () -> loanService.borrowBook(validBorrowerId, validBookId));
//	}
	
//	@Test
//	void concurrentBorrowAttempt_ShouldPreventDuplicateLoans() throws Exception {
//	    final int threadCount = 3;
//	    final CountDownLatch latch = new CountDownLatch(threadCount);
//	    final ExecutorService executor = Executors.newFixedThreadPool(threadCount);
//
//	    AtomicInteger successCount = new AtomicInteger(0);
//	    AtomicInteger failureCount = new AtomicInteger(0);
//
//	    for (int i = 0; i < threadCount; i++) {
//	        executor.execute(() -> {
//	            try {
//	                // Create new transaction for each thread
//	                transactionTemplate.execute(status -> {
//	                    try {
//	                        loanService.borrowBook(testBorrower.getId(), testBook.getId());
//	                        successCount.incrementAndGet();
//	                    } catch (BookAlreadyBorrowedException e) {
//	                        failureCount.incrementAndGet();
//	                    }
//	                    return null;
//	                });
//	            } finally {
//	                latch.countDown();
//	            }
//	        });
//	    }
//
//	    latch.await(2, TimeUnit.SECONDS);
//	    executor.shutdown();
//	    
//	    assertEquals(1, successCount.get());
//	    assertEquals(threadCount - 1, failureCount.get());
//	}
}