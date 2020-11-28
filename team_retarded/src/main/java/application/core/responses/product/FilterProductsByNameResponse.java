package application.core.responses.product;

import application.core.responses.CoreError;
import application.core.responses.CoreResponse;
import application.items.Product;

import java.util.List;

public class FilterProductsByNameResponse extends CoreResponse {

    private final List<Product> filterProductsByName;

    public FilterProductsByNameResponse(List<CoreError> errors, List<Product> filterProductsByName) {
        super(errors);
        this.filterProductsByName = filterProductsByName;
    }

    public List<Product> getFilterProductsByName() {
        return filterProductsByName;
    }
}
