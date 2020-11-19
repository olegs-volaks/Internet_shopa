package application.core.responses;

import application.items.Product;

import java.util.List;

public class GetProductByIdResponse extends CoreResponse {

    private Product product;

    public GetProductByIdResponse(Product product) {
        this.product = product;
    }

    public GetProductByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Product getProduct() {
        return product;
    }
}
