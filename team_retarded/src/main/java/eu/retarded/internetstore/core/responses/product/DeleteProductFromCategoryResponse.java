package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductFromCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class DeleteProductFromCategoryResponse extends CoreResponse<DeleteProductFromCategoryRequest> {

    private boolean productNotInCategory;

    public DeleteProductFromCategoryResponse(Set<ConstraintViolation<DeleteProductFromCategoryRequest>> errors) {
        super(errors);
    }

    public DeleteProductFromCategoryResponse(boolean productNotInCategory) {
        this.productNotInCategory = productNotInCategory;
    }

    public boolean productNotInCategory() {
        return productNotInCategory;
    }


}

