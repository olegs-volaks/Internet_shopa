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
        database.removeCategory(request.getCategoryId());
        // todo: Добавить проверку, удалился ли объект
        if (!errors.isEmpty()) {
            return new DeleteCategoryResponse(errors);
        }
        return new DeleteCategoryResponse(true);
    }
}
