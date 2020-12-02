package lv.javaguru.java2.library.core.responses;

import lv.javaguru.java2.library.Book;

import java.util.List;

public class SearchBooksResponse extends CoreResponse {

	private List<Book> books;

	public SearchBooksResponse(List<Book> books, List<CoreError> errors) {
		super(errors);
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}
}
