package application.core.responses.product;

import application.core.responses.CoreError;
import application.core.responses.CoreResponse;
import application.items.Product;

import java.util.List;
import java.util.Optional;

public class GetProductByIdResponse extends CoreResponse {

    private Optional<Product> productOptional;

    public GetProductByIdResponse(Optional<Product> productOptional) {
        this.productOptional = productOptional;
    }

    public GetProductByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Product> getProduct() {
        return  productOptional;
    }
}
