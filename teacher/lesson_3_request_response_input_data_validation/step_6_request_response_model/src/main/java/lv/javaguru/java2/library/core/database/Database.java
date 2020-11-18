package lv.javaguru.java2.library.core.database;

import lv.javaguru.java2.library.Book;

import java.util.List;

public interface Database {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

}
