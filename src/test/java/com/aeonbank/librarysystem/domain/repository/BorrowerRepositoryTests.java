package com.aeonbank.librarysystem.domain.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.aeonbank.librarysystem.domain.model.Borrower;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BorrowerRepositoryTests {

	@Autowired
	private BorrowerRepository borrowerRepository;

	private Borrower borrower;

	@BeforeEach
	public void setup() {
		borrower = new Borrower("Chua Soon Ee", "chuasoonee@yahoo.com");
		borrowerRepository.save(borrower);
	}

	@Test
	public void BorrowerRepository_FindByEmail_ReturnsBorrower() {
		// Act
		Optional<Borrower> foundBorrower = borrowerRepository.findByEmail("chuasoonee@yahoo.com");

		// Assert
		Assertions.assertThat(foundBorrower).isPresent();
		Assertions.assertThat(foundBorrower.get().getEmail()).isEqualTo("chuasoonee@yahoo.com");
	}

	@Test
	public void BorrowerRepository_FindByEmail_ReturnsEmptyOptional() {
		// Act
		Optional<Borrower> foundBorrower = borrowerRepository.findByEmail("nonexistent@aeonbank.com");

		// Assert
		Assertions.assertThat(foundBorrower).isEmpty();
	}

	@Test
	public void BorrowerRepository_Save_ReturnsSavedBorrower() {
		// Arrange
		Borrower newBorrower = new Borrower("Adrian Tee", "adrian.tee@aeonbank.com");

		// Act
		Borrower savedBorrower = borrowerRepository.save(newBorrower);

		// Assert
		Assertions.assertThat(savedBorrower).isNotNull();
		Assertions.assertThat(savedBorrower.getId()).isNotNull();
		Assertions.assertThat(savedBorrower.getEmail()).isEqualTo("adrian.tee@aeonbank.com");
	}

	@Test
	public void BorrowerRepository_FindById_ReturnsBorrower() {
		// Act
		Optional<Borrower> foundBorrower = borrowerRepository.findById(borrower.getId());

		// Assert
		Assertions.assertThat(foundBorrower).isPresent();
		Assertions.assertThat(foundBorrower.get().getName()).isEqualTo("Chua Soon Ee");
	}

	@Test
	public void BorrowerRepository_FindById_ReturnsEmptyOptional() {
		// Act
		Optional<Borrower> foundBorrower = borrowerRepository.findById(999L); // Non-existent ID

		// Assert
		Assertions.assertThat(foundBorrower).isEmpty();
	}
}