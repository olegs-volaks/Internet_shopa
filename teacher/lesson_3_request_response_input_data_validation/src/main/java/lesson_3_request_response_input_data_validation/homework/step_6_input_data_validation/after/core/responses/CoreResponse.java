package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.after.core.responses;

import java.util.List;

abstract class CoreResponse {

	private List<CoreError> errors;

	public CoreResponse() { }

	public CoreResponse(List<CoreError> errors) {
		this.errors = errors;
	}

	public List<CoreError> getErrors() {
		return errors;
	}

	public boolean hasErrors() {
		return errors != null && !errors.isEmpty();
	}
}