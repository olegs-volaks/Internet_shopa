package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.after.core.responses;

import java.util.List;

import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.after.Book;

public class GetAllBooksResponse extends CoreResponse {

	private List<Book> books;

	public GetAllBooksResponse(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}
}
