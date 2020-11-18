package lv.javaguru.java2.library.core.responses;

import lv.javaguru.java2.library.Book;

import java.util.List;

public class GetAllBooksResponse {

	private List<Book> books;

	public GetAllBooksResponse(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}
}
