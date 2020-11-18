package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.services;

import java.util.List;

import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.Book;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.database.Database;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.requests.GetAllBooksRequest;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.responses.GetAllBooksResponse;

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