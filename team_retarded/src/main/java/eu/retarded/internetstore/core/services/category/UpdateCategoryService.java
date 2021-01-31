package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.UpdateCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.UpdateCategoryResponse;
import eu.retarded.internetstore.core.services.validators.category.UpdateCategoryValidator;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UpdateCategoryService {

    @Autowired
    private CategoriesDatabase categoriesDatabase;

    @Autowired
    private UpdateCategoryValidator validator;

    @Transactional
    public UpdateCategoryResponse execute(UpdateCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCategoryResponse(errors);
        }
        long id = request.getId();

        Category resultCategory = new Category();

        resultCategory.setId(id);
        resultCategory.setName(request.getName());

        categoriesDatabase.updateCategory(resultCategory);
        return new UpdateCategoryResponse(id);
    }
}
