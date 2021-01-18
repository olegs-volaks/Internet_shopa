package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

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
