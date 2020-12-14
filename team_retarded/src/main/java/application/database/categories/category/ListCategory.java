package application.database.categories.category;

import java.util.List;
import java.util.function.Predicate;

public interface ListCategory<T> {

    long getId();

    void setId(long id);

    String getName();

    void setName(String name);

    List<T> getList();

    void add(T t);

    boolean remove(T t);

    void remove(Predicate<T> predicate);
}
