package teacher.lesson_3.homework.step_5_srp_request_response.after.core.requests;

public class RemoveBookRequest {

	private Long bookIdToRemove;

	public RemoveBookRequest(Long bookIdToRemove) {
		this.bookIdToRemove = bookIdToRemove;
	}

	public Long getBookIdToRemove() {
		return bookIdToRemove;
	}
}
