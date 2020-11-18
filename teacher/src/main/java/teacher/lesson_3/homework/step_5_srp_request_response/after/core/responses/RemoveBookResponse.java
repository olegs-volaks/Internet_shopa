package teacher.lesson_3.homework.step_5_srp_request_response.after.core.responses;

public class RemoveBookResponse {

	private boolean bookRemoved;

	public RemoveBookResponse(boolean bookRemoved) {
		this.bookRemoved = bookRemoved;
	}

	public boolean isBookRemoved() {
		return bookRemoved;
	}
}