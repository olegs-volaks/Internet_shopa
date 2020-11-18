package lesson_2_single_responsibility_principle.homework.step_2_srp_level_class_interface.after;

import java.util.List;

public interface Database {

	void save(Book book);

	void deleteById(Long id);

	List<Book> getAllBooks();

}
