package eu.retarded.internetstore.matchers;

import eu.retarded.internetstore.core.domain.Category;
import org.mockito.ArgumentMatcher;

public class ListProductCategoryMatcher implements ArgumentMatcher<Category> {

    private final String name;

    public ListProductCategoryMatcher(String name) {
        this.name = name;
    }

    @Override
    public boolean matches(Category argument) {
        return argument.getName().equals(name);
    }
}
