package lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.core.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lesson_3_request_response_input_data_validation.homework.step_6_input_data_validation.before.Book;

public class InMemoryDatabaseImpl implements Database {

	private Long nextId = 1L;
	private List<Book> books = new ArrayList<>();

	@Override
	public void save(Book book) {
		book.setId(nextId);
		nextId++;
		books.add(book);
	}

	@Override
	public boolean deleteById(Long id) {
		boolean isBookDeleted = false;
		Optional<Book> bookToDeleteOpt = books.stream()
				.filter(book -> book.getId().equals(id))
				.findFirst();
		if (bookToDeleteOpt.isPresent()) {
			Book bookToRemove = bookToDeleteOpt.get();
			isBookDeleted = books.remove(bookToRemove);
		}
		return isBookDeleted;
	}

	@Override
	public List<Book> getAllBooks() {
		return books;
	}
}
