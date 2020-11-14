package lesson_2_single_responsibility_principle.homework.step_3_srp_level_package.after.database;

import java.util.List;

import lesson_2_single_responsibility_principle.homework.step_3_srp_level_package.after.Book;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
