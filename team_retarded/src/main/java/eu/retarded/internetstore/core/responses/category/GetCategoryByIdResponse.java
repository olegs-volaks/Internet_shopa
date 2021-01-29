package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class GetCategoryByIdResponse extends CoreResponse {

    private  Category category;

    public GetCategoryByIdResponse(Category category) {
        this.category = category;
    }

    public GetCategoryByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Category getCategory() {
        return category;
    }
}