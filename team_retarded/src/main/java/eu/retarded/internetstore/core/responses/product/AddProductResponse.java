package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class AddProductResponse extends CoreResponse<AddProductRequest> {

    private long productId;

    public AddProductResponse(long productId) {
        this.productId = productId;
    }

    public AddProductResponse(Set<ConstraintViolation<AddProductRequest>> errors) {
        super(errors);
    }

    public long getProductId() {
        return productId;
    }
}
