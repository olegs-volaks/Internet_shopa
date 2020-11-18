package lesson_3_request_response_input_data_validation.code.after.database;

import lesson_3_request_response_input_data_validation.code.after.core.domain.Book;

import java.util.List;

public interface Database {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

}
