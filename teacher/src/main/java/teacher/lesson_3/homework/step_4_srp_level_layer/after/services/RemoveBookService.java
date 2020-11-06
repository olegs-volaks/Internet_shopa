package teacher.lesson_3.homework.step_4_srp_level_layer.after.services;

import teacher.lesson_3.homework.step_4_srp_level_layer.after.Database;

public class RemoveBookService {

	private Database database;

	public RemoveBookService(Database database) {
		this.database = database;
	}

	public void execute(Long bookId) {
		database.deleteById(bookId);
	}

}