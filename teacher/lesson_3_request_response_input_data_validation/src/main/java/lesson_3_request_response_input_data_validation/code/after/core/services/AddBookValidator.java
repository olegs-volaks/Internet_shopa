package lesson_3_request_response_input_data_validation.code.after.core.services;

import java.util.ArrayList;
import java.util.List;

import lesson_3_request_response_input_data_validation.code.after.core.requests.AddBookRequest;
import lesson_3_request_response_input_data_validation.code.after.core.responses.CoreError;

public class AddBookValidator {

	public List<CoreError> validate(AddBookRequest request) {
		List<CoreError> errors = new ArrayList<>();

		String title = request.getTitle();
		if (title == null || title.isEmpty()) {
			errors.add(new CoreError("title", "Must not be empty!"));
		}

		String author = request.getAuthor();
		if (author == null || author.isEmpty()) {
			errors.add(new CoreError("author", "Must not be empty!"));
		}

		return errors;
	}

}
