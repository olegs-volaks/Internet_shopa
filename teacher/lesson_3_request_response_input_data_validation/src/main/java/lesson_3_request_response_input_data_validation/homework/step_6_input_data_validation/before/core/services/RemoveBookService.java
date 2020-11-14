package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.services;

import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.database.Database;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.requests.RemoveBookRequest;
import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.responses.RemoveBookResponse;

public class RemoveBookService {

	private Database database;

	public RemoveBookService(Database database) {
		this.database = database;
	}

	public RemoveBookResponse execute(RemoveBookRequest request) {
		boolean isBookRemoved = database.deleteById(request.getBookIdToRemove());
		return new RemoveBookResponse(isBookRemoved);
	}

}