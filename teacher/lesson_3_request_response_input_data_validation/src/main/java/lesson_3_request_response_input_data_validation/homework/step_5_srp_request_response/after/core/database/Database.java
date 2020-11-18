package lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.after.core.database;

import java.util.List;

import lesson_3_request_response_input_data_validation.homework.step_5_srp_request_response.after.Book;

public interface Database {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

}
