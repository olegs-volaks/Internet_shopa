package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.UpdateCategoryRequest;
import eu.retarded.internetstore.core.responses.category.UpdateCategoryResponse;
import eu.retarded.internetstore.database.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UpdateCategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public UpdateCategoryResponse execute(UpdateCategoryRequest request) {
        Set<ConstraintViolation<UpdateCategoryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCategoryResponse(errors);
        }

        Category resultCategory = categoryRepository.getOne(request.getId());
        resultCategory.setName(request.getName());
        return new UpdateCategoryResponse(resultCategory);
    }
}
