package lesson_3_request_response_input_data_validation.code.before.services;

import lesson_3_request_response_input_data_validation.code.before.Book;
import lesson_3_request_response_input_data_validation.code.before.database.Database;

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
