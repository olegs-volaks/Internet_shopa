package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.responses;

public class RemoveBookResponse {

	private boolean bookRemoved;

	public RemoveBookResponse(boolean bookRemoved) {
		this.bookRemoved = bookRemoved;
	}

	public boolean isBookRemoved() {
		return bookRemoved;
	}
}