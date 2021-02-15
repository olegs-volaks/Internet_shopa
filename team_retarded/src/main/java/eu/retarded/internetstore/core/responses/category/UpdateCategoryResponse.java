package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.UpdateCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateCategoryResponse extends CoreResponse<UpdateCategoryRequest> {

    private Category category;

    public Category getCategory() {
        return category;
    }

    public UpdateCategoryResponse(Set<ConstraintViolation<UpdateCategoryRequest>> errors) {
        super(errors);
    }

    public UpdateCategoryResponse(Category category) {
        this.category = category;
    }
}
