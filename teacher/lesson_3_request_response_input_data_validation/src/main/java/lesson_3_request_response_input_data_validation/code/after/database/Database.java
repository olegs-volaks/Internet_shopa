package lesson_3_request_response_input_data_validation.code.after.database;

import java.util.List;

import lesson_3_request_response_input_data_validation.code.after.core.domain.Book;

public interface Database {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

}
