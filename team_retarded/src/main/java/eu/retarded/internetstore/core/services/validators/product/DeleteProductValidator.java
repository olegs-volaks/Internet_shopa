package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteProductValidator {

    @Autowired
    private ProductDatabase productDatabase;

    public List<CoreError> validate(DeleteProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteProductRequest request) {
        if (request.getProductId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty, negative or fractional"));
        }
        if (!productDatabase.isExist(request.getProductId())) {
            return Optional.of(new CoreError("ProductID", "Product with this ID does not exist"));
        }
        return Optional.empty();
    }
}

