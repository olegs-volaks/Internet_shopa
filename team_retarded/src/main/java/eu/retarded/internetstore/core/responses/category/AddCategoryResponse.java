package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class AddCategoryResponse extends CoreResponse<AddCategoryRequest> {

    private long categoryId;

    public AddCategoryResponse(Set<ConstraintViolation<AddCategoryRequest>> errors) {
        super(errors);
    }

    public AddCategoryResponse(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCategoryId() {
        return categoryId;
    }
}
