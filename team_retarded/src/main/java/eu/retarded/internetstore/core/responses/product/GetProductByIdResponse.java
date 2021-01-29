package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

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
