package application.core.responses.category;

import application.core.responses.CoreError;
import application.core.responses.CoreResponse;

import java.util.List;

public class DeleteCategoryResponse extends CoreResponse {

    private boolean isDeleted;

    public DeleteCategoryResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public DeleteCategoryResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
