package application.core.responses.product;

import application.core.responses.CoreError;
import application.core.responses.CoreResponse;
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
