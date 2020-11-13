package lesson_3_request_response_input_data_validation.code.before.services;

import java.util.List;

import lesson_3_request_response_input_data_validation.code.before.Book;
import lesson_3_request_response_input_data_validation.code.before.database.Database;

public class GetAllBooksService {

	private Database database;

	public GetAllBooksService(Database database) {
		this.database = database;
	}

	public List<Book> execute() {
		return database.getAllBooks();
	}

}