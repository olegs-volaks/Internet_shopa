package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.requests.product.UpdateProductRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateProductResponse extends CoreResponse<UpdateProductRequest> {

    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public UpdateProductResponse(Set<ConstraintViolation<UpdateProductRequest>> errors) {
        super(errors);
    }

    public UpdateProductResponse(Long productId) {
        this.productId = productId;
    }
}
