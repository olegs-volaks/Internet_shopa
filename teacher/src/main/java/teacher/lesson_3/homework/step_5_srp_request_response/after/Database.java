package teacher.lesson_3.homework.step_5_srp_request_response.after;

import java.util.List;

public interface Database {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

}
