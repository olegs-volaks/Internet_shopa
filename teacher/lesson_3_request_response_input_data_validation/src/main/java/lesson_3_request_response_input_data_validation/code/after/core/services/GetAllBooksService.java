package lesson_3_request_response_input_data_validation.code.after.core.services;

import java.util.List;

import lesson_3_request_response_input_data_validation.code.after.core.domain.Book;
import lesson_3_request_response_input_data_validation.code.after.database.Database;
import lesson_3_request_response_input_data_validation.code.after.core.requests.GetAllBooksRequest;
import lesson_3_request_response_input_data_validation.code.after.core.responses.GetAllBooksResponse;

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