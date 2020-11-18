package teacher.lesson_3.homework.step_5_srp_request_response.before.services;

import teacher.lesson_3.homework.step_5_srp_request_response.before.Database;

public class RemoveBookService {

	private Database database;

	public RemoveBookService(Database database) {
		this.database = database;
	}

	public void execute(Long bookId) {
		database.deleteById(bookId);
	}

}