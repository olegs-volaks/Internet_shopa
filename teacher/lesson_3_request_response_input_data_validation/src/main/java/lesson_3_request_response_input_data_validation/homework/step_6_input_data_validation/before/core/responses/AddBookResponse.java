package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.responses;

import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.Book;

public class AddBookResponse {

	private Book newBook;

	public AddBookResponse(Book newBook) {
		this.newBook = newBook;
	}

	public Book getNewBook() {
		return newBook;
	}
}