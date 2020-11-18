package lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.services;

import lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.Book;
import lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.database.Database;

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