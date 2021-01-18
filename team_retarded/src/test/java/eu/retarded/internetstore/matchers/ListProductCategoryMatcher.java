package eu.retarded.internetstore.matchers;

import eu.retarded.internetstore.database.categories.category.ProductListCategory;
import org.mockito.ArgumentMatcher;

public class ListProductCategoryMatcher implements ArgumentMatcher<ProductListCategory> {

    private final String name;

    public ListProductCategoryMatcher(String name) {
        this.name = name;
    }

    @Override
    public boolean matches(ProductListCategory argument) {
        return argument.getName().equals(name);
    }
}
