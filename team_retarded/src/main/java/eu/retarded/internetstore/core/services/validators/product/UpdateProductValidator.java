package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.UpdateProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateProductValidator {

    @Autowired
    private ProductDatabase productDatabase;

    public List<CoreError> validate(UpdateProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        validateDescription(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(UpdateProductRequest request) {
        if (request.getId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        if (!productDatabase.isExist(request.getId())) {
            return Optional.of(new CoreError("ID", "The product with the given id does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateName(UpdateProductRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return Optional.of(new CoreError("Name", "Must not be empty!"));
        }

        if (request.getName().length() < 3 || request.getName().length() > 100) {
            return Optional.of(new CoreError("Name", "Must be between 3 and 100 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDescription(UpdateProductRequest request) {
        if (request.getDescription() == null || request.getDescription().isEmpty()) {
            return Optional.of(new CoreError("Description", "Must not be empty!"));
        }

        if (request.getDescription().length() < 10 || request.getDescription().length() > 2000) {
            return Optional.of(new CoreError("Description", "Must be between 10 and 2000 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validatePrice(UpdateProductRequest request) {
        return (request.getPrice().doubleValue() <= 0 || request.getPrice().doubleValue() >= 1000000000)
                ? Optional.of(new CoreError("Price", "Must be between 0 and 1000000000"))
                : Optional.empty();
    }
}

