package lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.before.services;

import lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.before.Book;
import lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.before.database.Database;

import java.util.List;

public class GetAllBooksService {

	private Database database;

	public GetAllBooksService(Database database) {
		this.database = database;
	}

	public List<Book> execute() {
		return database.getAllBooks();
	}

}