package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.responses.category.AddCategoryResponse;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class AddCategoryService {

    @Autowired
    private CategoriesDatabase database;
    @Autowired
    private Validator validator;

    @Transactional
    public AddCategoryResponse execute(AddCategoryRequest request) {
        Set<ConstraintViolation<AddCategoryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCategoryResponse(errors);
        }
        return new AddCategoryResponse(database.addCategory(new Category(request.getName())));
    }
}
