package eu.retarded.internetstore.matchers;

import eu.retarded.internetstore.core.domain.ProductCategory;
import org.mockito.ArgumentMatcher;

public class ListProductCategoryMatcher implements ArgumentMatcher<ProductCategory> {

    private final String name;

    public ListProductCategoryMatcher(String name) {
        this.name = name;
    }

    @Override
    public boolean matches(ProductCategory argument) {
        return argument.getName().equals(name);
    }
}
