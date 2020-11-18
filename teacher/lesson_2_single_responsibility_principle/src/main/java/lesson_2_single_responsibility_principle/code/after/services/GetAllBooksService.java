package lesson_2_single_responsibility_principle.code.after.services;

import java.util.List;

import lesson_2_single_responsibility_principle.code.after.Book;
import lesson_2_single_responsibility_principle.code.after.database.Database;

public class GetAllBooksService {

	private Database database;

	public GetAllBooksService(Database database) {
		this.database = database;
	}

	public List<Book> execute() {
		return database.getAllBooks();
	}

}