package eu.retarded.internetstore.core.services.validators.basket;

import eu.retarded.internetstore.core.requests.basket.AddProductToBasketRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddProductToBasketValidator {

    public List<CoreError> validate(AddProductToBasketRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductID(request).ifPresent(errors::add);
        validateQuantity(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductID(AddProductToBasketRequest request) {
        if (request.getId() == null ) {
            return Optional.of(new CoreError("Name", "Must not be empty!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateQuantity(AddProductToBasketRequest request) {
        if (request.getQuantity() == null ) {
            return Optional.of(new CoreError("Password", "Must not be empty!"));
        }

        return Optional.empty();
    }


}
