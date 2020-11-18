package lesson_3_request_response_input_data_validation.code.after.core.services;

import java.util.ArrayList;
import java.util.List;

import lesson_3_request_response_input_data_validation.code.after.database.Database;
import lesson_3_request_response_input_data_validation.code.after.core.requests.RemoveBookRequest;
import lesson_3_request_response_input_data_validation.code.after.core.responses.CoreError;
import lesson_3_request_response_input_data_validation.code.after.core.responses.RemoveBookResponse;

public class RemoveBookService {

	private Database database;

	public RemoveBookService(Database database) {
		this.database = database;
	}

	public RemoveBookResponse execute(RemoveBookRequest request) {
		if (request.getBookIdToRemove() == null) {
			CoreError error = new CoreError("id", "Must not be empty!");
			List<CoreError> errors = new ArrayList<>();
			errors.add(error);
			return new RemoveBookResponse(errors);
		}
		boolean isBookRemoved = database.deleteById(request.getBookIdToRemove());
		return new RemoveBookResponse(isBookRemoved);
	}

}