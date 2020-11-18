package teacher.lesson_3.homework.step_5_srp_request_response.before.services;

import java.util.List;

import teacher.lesson_3.homework.step_5_srp_request_response.before.Book;
import teacher.lesson_3.homework.step_5_srp_request_response.before.Database;

public class GetAllBooksService {

	private Database database;

	public GetAllBooksService(Database database) {
		this.database = database;
	}

	public List<Book> execute() {
		return database.getAllBooks();
	}

}