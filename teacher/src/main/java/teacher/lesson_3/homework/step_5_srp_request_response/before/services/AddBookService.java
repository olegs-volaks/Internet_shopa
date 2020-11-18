package teacher.lesson_3.homework.step_5_srp_request_response.before.services;

import teacher.lesson_3.homework.step_5_srp_request_response.before.Book;
import teacher.lesson_3.homework.step_5_srp_request_response.before.Database;

public class AddBookService {

	private Database database;

	public AddBookService(Database database) {
		this.database = database;
	}

	public void execute(String bookTitle, String bookAuthor) {
		Book book = new Book(bookTitle, bookAuthor);
		database.save(book);
	}

}
