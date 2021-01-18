package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class AddCategoryResponse extends CoreResponse {

    private long categoryId;

    public AddCategoryResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddCategoryResponse(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getCategoryId() {
        return categoryId;
    }
}
