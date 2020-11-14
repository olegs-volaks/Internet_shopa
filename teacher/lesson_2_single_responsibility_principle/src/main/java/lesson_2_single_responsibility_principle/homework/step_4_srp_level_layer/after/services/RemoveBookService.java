package lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.services;

import lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.database.Database;

public class RemoveBookService {

	private Database database;

	public RemoveBookService(Database database) {
		this.database = database;
	}

	public void execute(Long bookId) {
		database.deleteById(bookId);
	}

}