package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class DeleteProductResponse extends CoreResponse<DeleteProductRequest> {

    private boolean isDeleted;

    public DeleteProductResponse(Set<ConstraintViolation<DeleteProductRequest>> errors) {
        super(errors);
    }

    public DeleteProductResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
