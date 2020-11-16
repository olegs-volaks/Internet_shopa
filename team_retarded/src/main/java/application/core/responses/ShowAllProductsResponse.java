package application.core.responses;

import application.items.Product;

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
