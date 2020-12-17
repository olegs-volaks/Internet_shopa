package eu.retarded.internetstore.matchers;

import eu.retarded.internetstore.core.domain.Product;
import org.mockito.ArgumentMatcher;

public class ProductMatcher implements ArgumentMatcher<Product> {

    private final String name;
    private final String description;

    public ProductMatcher(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean matches(Product argument) {
        return argument.getName().equals(name) &&
                argument.getDescription().equals(description);
    }
}
