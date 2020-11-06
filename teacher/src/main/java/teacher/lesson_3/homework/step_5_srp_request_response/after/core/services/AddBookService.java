package teacher.lesson_3.homework.step_5_srp_request_response.after.core.services;

import teacher.lesson_3.homework.step_5_srp_request_response.after.Book;
import teacher.lesson_3.homework.step_5_srp_request_response.after.Database;
import teacher.lesson_3.homework.step_5_srp_request_response.after.core.requests.AddBookRequest;
import teacher.lesson_3.homework.step_5_srp_request_response.after.core.responses.AddBookResponse;

public class AddBookService {

	private Database database;

	public AddBookService(Database database) {
		this.database = database;
	}

	public AddBookResponse execute(AddBookRequest request) {
		Book book = new Book(request.getTitle(), request.getAuthor());
		database.save(book);
		return new AddBookResponse(book);
	}

}
