package lesson_3_request_response_input_data_validation.code.after.core.responses;

public class CoreError {

	private String field;
	private String message;

	public CoreError(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
}
