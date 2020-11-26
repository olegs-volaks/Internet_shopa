package application.core.responses;

import application.items.Product;

import java.util.List;

public class SearchProductResponse extends CoreResponse {

    private final List<Product> products;

    public SearchProductResponse(List<CoreError> errors, List<Product> products) {
        super(errors);
        this.products = products;
    }


    public List<Product> getProducts() {
        return products;
    }
}
