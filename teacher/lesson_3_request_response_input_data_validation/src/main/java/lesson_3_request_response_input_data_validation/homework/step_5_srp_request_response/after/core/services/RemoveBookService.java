package lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.after.core.services;

import lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.after.core.database.Database;
import lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.after.core.requests.RemoveBookRequest;
import lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.after.core.responses.RemoveBookResponse;

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