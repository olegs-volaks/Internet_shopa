package teacher.lesson_2.homework.step_3_srp_level_package.after;

import java.util.List;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
