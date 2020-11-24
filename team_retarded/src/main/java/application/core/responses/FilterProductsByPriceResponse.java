package application.core.responses;

import application.items.Product;

import java.util.List;

public class FilterProductsByPriceResponse extends CoreResponse{

    private final List <Product> productsByFilter;

    public FilterProductsByPriceResponse(List<CoreError> errors,List <Product> productsByFilter) {
        super(errors);
        this.productsByFilter = productsByFilter;
    }

    public List <Product> getProductsByFilter() {
        return productsByFilter;
    }
}
