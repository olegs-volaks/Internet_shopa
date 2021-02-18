package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class AddCategoryResponse extends CoreResponse<AddCategoryRequest> {

    private Category category;

    public AddCategoryResponse(Set<ConstraintViolation<AddCategoryRequest>> errors) {
        super(errors);
    }

    public AddCategoryResponse(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
