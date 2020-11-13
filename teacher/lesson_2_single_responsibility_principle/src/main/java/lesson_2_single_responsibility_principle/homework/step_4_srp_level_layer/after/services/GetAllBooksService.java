package lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.services;

import java.util.List;

import lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.Book;
import lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.database.Database;

public class GetAllBooksService {

	private Database database;

	public GetAllBooksService(Database database) {
		this.database = database;
	}

	public List<Book> execute() {
		return database.getAllBooks();
	}

}