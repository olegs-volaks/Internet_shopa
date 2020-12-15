package lv.javaguru.java2.library.core.services;

import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.domain.Book;
import lv.javaguru.java2.library.core.requests.GetAllBooksRequest;
import lv.javaguru.java2.library.core.responses.GetAllBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllBooksService {

	@Autowired private Database database;

	public GetAllBooksResponse execute(GetAllBooksRequest request) {
		List<Book> books = database.getAllBooks();
		return new GetAllBooksResponse(books);
	}

}