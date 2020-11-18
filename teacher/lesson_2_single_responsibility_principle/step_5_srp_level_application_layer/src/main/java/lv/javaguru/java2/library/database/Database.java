package lv.javaguru.java2.library.database;

import lv.javaguru.java2.library.Book;

import java.util.List;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
