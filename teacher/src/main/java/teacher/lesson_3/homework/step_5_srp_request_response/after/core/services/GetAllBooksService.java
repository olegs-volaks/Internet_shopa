package teacher.lesson_3.homework.step_5_srp_request_response.after.core.services;

import java.util.List;

import teacher.lesson_3.homework.step_5_srp_request_response.after.Book;
import teacher.lesson_3.homework.step_5_srp_request_response.after.Database;
import teacher.lesson_3.homework.step_5_srp_request_response.after.core.requests.GetAllBooksRequest;
import teacher.lesson_3.homework.step_5_srp_request_response.after.core.responses.GetAllBooksResponse;

public class GetAllBooksService {

	private Database database;

	public GetAllBooksService(Database database) {
		this.database = database;
	}

	public GetAllBooksResponse execute(GetAllBooksRequest request) {
		List<Book> books = database.getAllBooks();
		return new GetAllBooksResponse(books);
	}

}