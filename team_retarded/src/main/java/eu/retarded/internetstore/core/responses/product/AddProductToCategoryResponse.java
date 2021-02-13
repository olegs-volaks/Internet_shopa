package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.requests.product.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class AddProductToCategoryResponse extends CoreResponse<AddProductToCategoryRequest> {

    private boolean productInCategory;

    public AddProductToCategoryResponse(Set<ConstraintViolation<AddProductToCategoryRequest>> errors) {
        super(errors);
    }

    public AddProductToCategoryResponse(boolean productInCategory) {
        this.productInCategory = productInCategory;
    }

    public boolean productInCategory() {
        return productInCategory;
    }

}
