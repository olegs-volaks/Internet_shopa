package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;
import java.util.Optional;

public class GetCategoryByIdResponse extends CoreResponse {

    private Optional <Category> categoryOptional;

    public GetCategoryByIdResponse(Optional <Category> categoryOptional) {
        this.categoryOptional = categoryOptional;
    }

    public GetCategoryByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Category> getCategory() {
        return categoryOptional;
    }
}