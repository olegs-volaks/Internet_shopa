package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GetCategoryByIdResponse extends CoreResponse<GetCategoryByIdRequest> {

    private Category category;

    public GetCategoryByIdResponse(Category category) {
        this.category = category;
    }

    public GetCategoryByIdResponse(Set<ConstraintViolation<GetCategoryByIdRequest>> errors) {
        super(errors);
    }

    public Category getCategory() {
        return category;
    }
}