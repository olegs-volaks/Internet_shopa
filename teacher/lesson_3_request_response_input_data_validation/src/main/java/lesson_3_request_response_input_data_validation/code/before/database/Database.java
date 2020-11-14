package lesson_3_request_response_input_data_validation.code.before.database;

import java.util.List;

import lesson_3_request_response_input_data_validation.code.before.Book;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
