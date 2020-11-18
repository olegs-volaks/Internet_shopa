package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.database;

import java.util.List;

import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.Book;

public interface Database {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

}
