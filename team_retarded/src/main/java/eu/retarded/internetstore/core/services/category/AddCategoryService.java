package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.responses.category.AddCategoryResponse;
import eu.retarded.internetstore.database.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class AddCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Validator validator;

    @Transactional
    public AddCategoryResponse execute(AddCategoryRequest request) {
        Set<ConstraintViolation<AddCategoryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCategoryResponse(errors);
        }
        Category category = new Category(request.getName());
        return new AddCategoryResponse(categoryRepository.save(category));
    }
}
