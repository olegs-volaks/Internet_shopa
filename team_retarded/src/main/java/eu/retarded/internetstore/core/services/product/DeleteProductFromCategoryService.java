package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductFromCategoryRequest;
import eu.retarded.internetstore.core.responses.product.DeleteProductFromCategoryResponse;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class DeleteProductFromCategoryService {
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private Validator validator;

    @Transactional
    public DeleteProductFromCategoryResponse execute(DeleteProductFromCategoryRequest request) {
        Set<ConstraintViolation<DeleteProductFromCategoryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductFromCategoryResponse(errors);
        }
        return new DeleteProductFromCategoryResponse(productDatabase.removeProductFromCategory(request.getProductId()));
    }
}
