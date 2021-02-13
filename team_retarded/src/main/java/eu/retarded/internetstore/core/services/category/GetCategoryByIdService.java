package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
import eu.retarded.internetstore.core.responses.category.GetCategoryByIdResponse;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class GetCategoryByIdService {
    @Autowired
    private CategoriesDatabase categoriesDatabase;
    @Autowired
    private Validator validator;

    @Transactional
    public GetCategoryByIdResponse execute(GetCategoryByIdRequest request) {
        Set<ConstraintViolation<GetCategoryByIdRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetCategoryByIdResponse(errors);
        }
        return new GetCategoryByIdResponse(categoriesDatabase.getCategory(request.getCategoryId()).get());
    }
}

