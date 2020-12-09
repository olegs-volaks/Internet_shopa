package application.core.services.validators.product;

import application.core.requests.product.DeleteProductRequest;
import application.core.responses.CoreError;
import com.retarded.di.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class DeleteProductValidator {

    public List<CoreError> validate(DeleteProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteProductRequest request) {
        if (request.getProductIdToDelete() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty, negative or fractional"));
        }

        return Optional.empty();
    }

}

