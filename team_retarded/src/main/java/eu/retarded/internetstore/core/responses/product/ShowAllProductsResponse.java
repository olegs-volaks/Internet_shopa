package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class ShowAllProductsResponse extends CoreResponse {

    private List<Product> products;

    public ShowAllProductsResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
