package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.requests;

public class AddBookRequest {

	private String title;
	private String author;

	public AddBookRequest(String title, String author) {
		this.title = title;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}
}
