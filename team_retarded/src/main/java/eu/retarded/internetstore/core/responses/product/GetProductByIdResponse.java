package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

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
        return productOptional;
    }
}
