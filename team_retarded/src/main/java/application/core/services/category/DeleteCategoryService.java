package application.core.services.category;

import application.core.requests.category.DeleteCategoryRequest;
import application.core.responses.CoreError;
import application.core.responses.category.DeleteCategoryResponse;
import application.core.services.validators.category.DeleteCategoryValidator;
import application.database.categories.database.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteCategoryService {

    @Autowired private CategoriesDatabase database;
    @Autowired private DeleteCategoryValidator validator;

    public DeleteCategoryResponse execute(DeleteCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteCategoryResponse(errors);
        }
        if (!database.isExist(request.getCategoryId())) {
            errors.add(new CoreError("ID", "The category with the given id does not exist"));
            return new DeleteCategoryResponse(errors);
        }
        return new DeleteCategoryResponse(database.removeCategory(request.getCategoryId()));
    }
}
