package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.requests.category.UpdateCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateCategoryResponse extends CoreResponse<UpdateCategoryRequest> {

    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public UpdateCategoryResponse(Set<ConstraintViolation<UpdateCategoryRequest>> errors) {
        super(errors);
    }

    public UpdateCategoryResponse(Long categoryId) {
        this.categoryId = categoryId;
    }
}
