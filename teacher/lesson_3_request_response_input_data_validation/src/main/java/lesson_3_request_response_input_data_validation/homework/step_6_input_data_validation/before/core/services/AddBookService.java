package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.services;

import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.Book;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.database.Database;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.requests.AddBookRequest;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.responses.AddBookResponse;

public class AddBookService {

	private Database database;

	public AddBookService(Database database) {
		this.database = database;
	}

	public AddBookResponse execute(AddBookRequest request) {
		Book book = new Book(request.getTitle(), request.getAuthor());
		database.save(book);
		return new AddBookResponse(book);
	}

}
