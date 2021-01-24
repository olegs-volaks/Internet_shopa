package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductFromCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteProductFromCategoryValidator {
    @Autowired
    private ProductDatabase productDatabase;

    public List<CoreError> validate(DeleteProductFromCategoryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductId(DeleteProductFromCategoryRequest request) {
        if (request.getProductId() <= 0) {
            return Optional.of(new CoreError("ProductID", "Must not be empty or negative"));
        }
        if (!productDatabase.isExist(request.getProductId())) {
            return Optional.of(new CoreError("ProductID", "Product with this ID does not exist"));
        }
        return Optional.empty();
    }

}

