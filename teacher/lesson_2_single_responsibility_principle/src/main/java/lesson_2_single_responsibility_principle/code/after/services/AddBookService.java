package lesson_2_single_responsibility_principle.code.after.services;

import lesson_2_single_responsibility_principle.code.after.Book;
import lesson_2_single_responsibility_principle.code.after.database.Database;

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
