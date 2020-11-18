package lesson_3_request_response_input_data_validation.code.after.core.responses;

import lesson_3_request_response_input_data_validation.code.after.core.domain.Book;

import java.util.List;

public class GetAllBooksResponse extends CoreResponse {

	private List<Book> books;

	public GetAllBooksResponse(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}
}
