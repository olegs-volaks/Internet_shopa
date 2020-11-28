package application.database.categories.category;

import java.util.List;
import java.util.function.Predicate;

public interface ListCategory<T> {

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    List<T> get();

    void add(T t);

    void remove(T t);

    void remove(Predicate<T> predicate);
}
