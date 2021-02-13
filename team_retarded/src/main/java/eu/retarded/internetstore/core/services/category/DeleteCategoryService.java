package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.DeleteCategoryRequest;
import eu.retarded.internetstore.core.responses.category.DeleteCategoryResponse;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class DeleteCategoryService {

    @Autowired
    private CategoriesDatabase database;
    @Autowired
    private Validator validator;

    @Transactional
    public DeleteCategoryResponse execute(DeleteCategoryRequest request) {
        Set<ConstraintViolation<DeleteCategoryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteCategoryResponse(errors);
        }
        return new DeleteCategoryResponse(database.removeCategory(request.getCategoryId()));
    }
}
