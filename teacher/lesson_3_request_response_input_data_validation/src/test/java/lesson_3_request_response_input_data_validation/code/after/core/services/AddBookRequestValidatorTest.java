package lesson_3_request_response_input_data_validation.code.after.core.services;

import lesson_3_request_response_input_data_validation.code.after.core.requests.AddBookRequest;
import lesson_3_request_response_input_data_validation.code.after.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddBookRequestValidatorTest {

	private AddBookRequestValidator validator = new AddBookRequestValidator();

	@Test
	public void success() {
		AddBookRequest request = new AddBookRequest("Title", "Author");
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 0);
	}

	@Test
	public void shouldReturnErrorWhenTitleIsNull() {
		AddBookRequest request = new AddBookRequest(null, "Author");
		List<CoreError> errors = validator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getField(), "bookTitle");
		assertEquals(errors.get(0).getMessage(), "Must be not empty!");
	}

}