package teacher.lesson_3.homework.step_5_srp_request_response.before;

import java.util.List;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
