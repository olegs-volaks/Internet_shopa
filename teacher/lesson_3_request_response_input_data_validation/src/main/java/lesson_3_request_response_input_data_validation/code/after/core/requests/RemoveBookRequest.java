package lesson_3_request_response_input_data_validation.code.after.core.requests;

public class RemoveBookRequest {

	private Long bookIdToRemove;

	public RemoveBookRequest(Long bookIdToRemove) {
		this.bookIdToRemove = bookIdToRemove;
	}

	public Long getBookIdToRemove() {
		return bookIdToRemove;
	}
}
