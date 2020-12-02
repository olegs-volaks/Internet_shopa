package lv.javaguru.java2.library.matchers;

import lv.javaguru.java2.library.Book;
import org.mockito.ArgumentMatcher;

public class BookMatcher implements ArgumentMatcher<Book> {
	private String title;
	private String author;

	public BookMatcher(String title, String author) {
		this.title = title;
		this.author = author;
	}

	@Override
	public boolean matches(Book book) {
		return book.getTitle().equals(title)
				&& book.getAuthor().equals(author);
	}
}