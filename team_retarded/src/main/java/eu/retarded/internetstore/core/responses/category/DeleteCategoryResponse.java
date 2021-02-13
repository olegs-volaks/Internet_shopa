package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.requests.category.DeleteCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class DeleteCategoryResponse extends CoreResponse<DeleteCategoryRequest> {

    private boolean isDeleted;

    public DeleteCategoryResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public DeleteCategoryResponse(Set<ConstraintViolation<DeleteCategoryRequest>> errors) {
        super(errors);
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
