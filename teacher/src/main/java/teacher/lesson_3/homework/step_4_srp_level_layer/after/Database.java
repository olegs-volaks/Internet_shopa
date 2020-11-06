package teacher.lesson_3.homework.step_4_srp_level_layer.after;

import java.util.List;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
