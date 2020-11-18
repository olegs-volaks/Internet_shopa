package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.responses;

import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.Book;

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
