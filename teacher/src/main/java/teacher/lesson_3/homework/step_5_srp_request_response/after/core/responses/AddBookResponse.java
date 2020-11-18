package teacher.lesson_3.homework.step_5_srp_request_response.after.core.responses;

import teacher.lesson_3.homework.step_5_srp_request_response.after.Book;

public class AddBookResponse {

	private Book newBook;

	public AddBookResponse(Book newBook) {
		this.newBook = newBook;
	}

	public Book getNewBook() {
		return newBook;
	}
}