package com.aeonbank.librarysystem.utils;

public class IsbnUtils {

	/**
	 * Removes hyphens and whitespace. Converts to uppercase for 'X' compatibility.
	 */
	public static String normalize(String isbn) {
		if (isbn == null)
			return null;
		return isbn.replaceAll("[-\\s]", "").toUpperCase();
	}

	public static boolean isValidIsbn(String rawIsbn) {
		String isbn = normalize(rawIsbn);
		return isValidIsbn10(isbn) || isValidIsbn13(isbn);
	}

	public static boolean isValidIsbn10(String isbn) {
		if (isbn == null || isbn.length() != 10 || !isbn.matches("\\d{9}[\\dX]")) {
			return false;
		}

		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += (isbn.charAt(i) - '0') * (10 - i);
		}

		char lastChar = isbn.charAt(9);
		sum += (lastChar == 'X') ? 10 : (lastChar - '0');

		return sum % 11 == 0;
	}

	public static boolean isValidIsbn13(String isbn) {
		if (isbn == null || isbn.length() != 13 || !isbn.matches("\\d{13}")) {
			return false;
		}

		int sum = 0;
		for (int i = 0; i < 12; i++) {
			int digit = isbn.charAt(i) - '0';
			sum += (i % 2 == 0) ? digit : digit * 3;
		}

		int checkDigit = (10 - (sum % 10)) % 10;
		return checkDigit == (isbn.charAt(12) - '0');
	}
/*
	/**
	 * Unused 978-ISBNs were about to become extinct in 2019. Therefore U.S. ISBN
	 * Agency started assigning ISBNs with the 979 prefix in 2020. The major
	 * difference between 978 and 979 ISBNs is that the ones with the 979 prefix do
	 * not have a corresponding 10-digit ISBN. This means that a book with a 979
	 * prefix can only be identified by its 13-digit ISBN. The introduction of the
	 * 979 prefix has provided a new range of ISBNs that can be used to identify
	 * books, ensuring that there are enough unique combinations for the growing
	 * number of books being produced.
	 * 
	 * This conversion method is used to standardize the ISBN in the Aeon Bank
	 * Library System to 13-digit, hence converting the provided 10-digit ISBN to
	 * 13-digit with prefix 978.
	 * 
	 *
	public static String convertIsbn10to13(String isbn10) {
		if (!isValidIsbn10(isbn10)) {
			throw new IllegalArgumentException("Invalid ISBN-10");
		}

		String base = "978" + isbn10.substring(0, 9); // drop the old check digit
		int sum = 0;
		for (int i = 0; i < 12; i++) {
			int digit = base.charAt(i) - '0';
			sum += (i % 2 == 0) ? digit : digit * 3;
		}

		int checkDigit = (10 - (sum % 10)) % 10;
		return base + checkDigit;
	}

	public static String convertIsbn13to10(String isbn13) {
		String normalized = normalize(isbn13);

		if (!isValidIsbn13(normalized) || !normalized.startsWith("978")) {
			throw new IllegalArgumentException("Only ISBN-13 with 978 prefix can be converted to ISBN-10.");
		}

		String core = normalized.substring(3, 12); // drop '978' and last digit
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += (core.charAt(i) - '0') * (10 - i);
		}

		int remainder = 11 - (sum % 11);
		char checkDigit;
		if (remainder == 10)
			checkDigit = 'X';
		else if (remainder == 11)
			checkDigit = '0';
		else
			checkDigit = (char) ('0' + remainder);

		return core + checkDigit;
	}
	*/
}