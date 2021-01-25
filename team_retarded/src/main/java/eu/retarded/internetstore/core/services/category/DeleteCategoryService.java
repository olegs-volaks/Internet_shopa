package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.DeleteCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.DeleteCategoryResponse;
import eu.retarded.internetstore.core.services.validators.category.DeleteCategoryValidator;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteCategoryService {

    @Autowired
    private CategoriesDatabase database;
    @Autowired
    private DeleteCategoryValidator validator;

    public DeleteCategoryResponse execute(DeleteCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteCategoryResponse(errors);
        }
        return new DeleteCategoryResponse(database.removeCategory(request.getCategoryId()));
    }
}
