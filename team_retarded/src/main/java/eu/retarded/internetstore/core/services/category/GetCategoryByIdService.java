package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.GetCategoryByIdResponse;
import eu.retarded.internetstore.core.services.validators.category.GetCategoryByIdValidator;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetCategoryByIdService {
    @Autowired
    private CategoriesDatabase categoriesDatabase;
    @Autowired
    private GetCategoryByIdValidator validator;

    @Transactional
    public GetCategoryByIdResponse execute(GetCategoryByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetCategoryByIdResponse(errors);
        }
        return new GetCategoryByIdResponse(categoriesDatabase.getCategory(request.getCategoryId()).get());
    }
}

