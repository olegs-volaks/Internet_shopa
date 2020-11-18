package lesson_2_single_responsibility_principle.code.after.database;

import lesson_2_single_responsibility_principle.code.after.Book;

import java.util.List;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
