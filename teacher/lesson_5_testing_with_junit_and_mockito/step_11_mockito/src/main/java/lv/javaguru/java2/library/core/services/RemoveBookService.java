package lv.javaguru.java2.library.core.services;

import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.requests.RemoveBookRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.RemoveBookResponse;
import lv.javaguru.java2.library.core.services.validators.RemoveBookRequestValidator;

import java.util.List;

public class RemoveBookService {

	private Database database;
	private RemoveBookRequestValidator validator;

	public RemoveBookService(Database database,
							 RemoveBookRequestValidator validator) {
		this.database = database;
		this.validator = validator;
	}

	public RemoveBookResponse execute(RemoveBookRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new RemoveBookResponse(errors);
		}
		boolean isBookRemoved = database.deleteById(request.getBookId());
		return new RemoveBookResponse(isBookRemoved);
	}

}