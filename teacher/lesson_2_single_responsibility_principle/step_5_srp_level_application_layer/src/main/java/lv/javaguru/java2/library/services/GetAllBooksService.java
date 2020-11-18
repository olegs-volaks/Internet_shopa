package lv.javaguru.java2.library.services;

import lv.javaguru.java2.library.Book;
import lv.javaguru.java2.library.database.Database;

import java.util.List;

public class GetAllBooksService {

	private Database database;

	public GetAllBooksService(Database database) {
		this.database = database;
	}

	public List<Book> execute() {
		return database.getAllBooks();
	}

}