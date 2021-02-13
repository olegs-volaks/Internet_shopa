package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.ShowAllProductsInCategoryRequest;
import eu.retarded.internetstore.core.responses.category.ShowAllProductsInCategoryResponse;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ShowAllProductsInCategoryService {
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private Validator validator;

    @Transactional
    public ShowAllProductsInCategoryResponse execute(ShowAllProductsInCategoryRequest request) {
        Set<ConstraintViolation<ShowAllProductsInCategoryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShowAllProductsInCategoryResponse(errors, null);
        }
        return new ShowAllProductsInCategoryResponse(null, productDatabase.getList());
    }
}