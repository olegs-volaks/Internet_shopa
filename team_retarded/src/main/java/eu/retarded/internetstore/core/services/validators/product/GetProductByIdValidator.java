package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetProductByIdValidator {

    public List<CoreError> validate(GetProductByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateId(GetProductByIdRequest request) {
        if (request.getProductId() <= 0) {
            return Optional.of(new CoreError("ID", "Must be more than 0"));
        }
        return Optional.empty();
    }
}
