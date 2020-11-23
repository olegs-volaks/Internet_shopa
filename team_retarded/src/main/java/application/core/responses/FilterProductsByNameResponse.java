package application.core.responses;

import application.items.Product;

import java.util.List;

public class FilterProductsByNameResponse extends CoreResponse {

    private final List<Product> filterProductsByName;

    public FilterProductsByNameResponse(List<CoreError> errors,List <Product> filterProductsByName) {
        super(errors);
        this.filterProductsByName = filterProductsByName;
    }

    public List <Product> getFilterProductsByName() {
        return filterProductsByName;
    }
}

