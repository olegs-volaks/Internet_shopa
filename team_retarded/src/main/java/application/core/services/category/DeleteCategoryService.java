package application.core.services.category;

import application.core.requests.category.DeleteCategoryRequest;
import application.core.responses.CoreError;
import application.core.responses.category.DeleteCategoryResponse;
import application.core.services.validators.category.DeleteCategoryValidator;
import application.database.categories.database.CategoriesDatabase;

import java.util.List;

public class DeleteCategoryService {

    private final CategoriesDatabase database;
    private final DeleteCategoryValidator validator;

    public DeleteCategoryService(CategoriesDatabase database, DeleteCategoryValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeleteCategoryResponse execute(DeleteCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteCategoryResponse(errors);
        }
        if (database.getCategory(request.getCategoryId()).isEmpty()) {
            errors.add(new CoreError("ID", "The category with the given id does not exist"));
            return new DeleteCategoryResponse(errors);
        }
        database.removeCategory(request.getCategoryId());
        if (database.getCategory(request.getCategoryId()).isEmpty()) {
            return new DeleteCategoryResponse(true);
        }
        return new DeleteCategoryResponse(false);
    }
}
