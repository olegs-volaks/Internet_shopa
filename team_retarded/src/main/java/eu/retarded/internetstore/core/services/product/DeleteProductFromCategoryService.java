package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.DeleteProductFromCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.DeleteProductFromCategoryResponse;
import eu.retarded.internetstore.core.services.validators.product.DeleteProductFromCategoryValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DeleteProductFromCategoryService {
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private DeleteProductFromCategoryValidator validator;


    public DeleteProductFromCategoryResponse execute(DeleteProductFromCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductFromCategoryResponse(errors);
        }
        Optional<Product> productID = productDatabase.getById(request.DeleteProductFromCategoryProductId());
        return new DeleteProductFromCategoryResponse(productDatabase.removeProductFromCategory(productID.get().getId()));
    }
}
