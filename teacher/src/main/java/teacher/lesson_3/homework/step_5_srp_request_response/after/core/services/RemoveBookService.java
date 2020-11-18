package teacher.lesson_3.homework.step_5_srp_request_response.after.core.services;

import teacher.lesson_3.homework.step_5_srp_request_response.after.Database;
import teacher.lesson_3.homework.step_5_srp_request_response.after.core.requests.RemoveBookRequest;
import teacher.lesson_3.homework.step_5_srp_request_response.after.core.responses.RemoveBookResponse;

public class RemoveBookService {

	private Database database;

	public RemoveBookService(Database database) {
		this.database = database;
	}

	public RemoveBookResponse execute(RemoveBookRequest request) {
		boolean isBookRemoved = database.deleteById(request.getBookIdToRemove());
		return new RemoveBookResponse(isBookRemoved);
	}

}