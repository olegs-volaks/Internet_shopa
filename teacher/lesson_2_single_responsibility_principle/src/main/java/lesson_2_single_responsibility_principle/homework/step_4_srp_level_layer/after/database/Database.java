package lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.database;

import java.util.List;

import lesson_2_single_responsibility_principle.homework.step_4_srp_level_layer.after.Book;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
