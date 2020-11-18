package teacher.lesson_3.homework.step_4_srp_level_layer.after.services;

import teacher.lesson_3.homework.step_4_srp_level_layer.after.Book;
import teacher.lesson_3.homework.step_4_srp_level_layer.after.Database;

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
