package teacher.lesson_3.homework.step_5_srp_request_response.after.core.responses;

import java.util.List;

import teacher.lesson_3.homework.step_5_srp_request_response.after.Book;

public class GetAllBooksResponse {

	private List<Book> books;

	public GetAllBooksResponse(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}
}
