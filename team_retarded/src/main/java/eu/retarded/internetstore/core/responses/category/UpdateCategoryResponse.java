package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class UpdateCategoryResponse extends CoreResponse {
    private Long categoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public UpdateCategoryResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateCategoryResponse(Long categoryId) {
        this.categoryId = categoryId;
    }
}
