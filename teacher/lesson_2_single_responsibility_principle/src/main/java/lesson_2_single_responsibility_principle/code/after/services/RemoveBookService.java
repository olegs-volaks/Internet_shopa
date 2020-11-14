package lesson_2_single_responsibility_principle.code.after.services;

import lesson_2_single_responsibility_principle.code.after.database.Database;

public class RemoveBookService {

	private Database database;

	public RemoveBookService(Database database) {
		this.database = database;
	}

	public void execute(Long bookId) {
		database.deleteById(bookId);
	}

}