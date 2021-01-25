package eu.retarded.internetstore.core.services.validators.basket;

import eu.retarded.internetstore.core.requests.basket.AddProductToBasketRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.product.ProductDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddProductToBasketValidator {

    @Autowired
    private UsersDatabase usersDatabase;
    @Autowired
    private ProductDatabase productDatabase;

    public List<CoreError> validate(AddProductToBasketRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductID(request).ifPresent(errors::add);
        validateUserID(request).ifPresent(errors::add);
        validateQuantity(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductID(AddProductToBasketRequest request) {
        if (request.getProductId() <= 0) {
            return Optional.of(new CoreError("ProductID", "Must not be empty or negative"));
        }
        if (productDatabase.getById(request.getProductId()).isEmpty()) {
            return Optional.of(new CoreError("ProductID", "Product with this ID does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserID(AddProductToBasketRequest request) {
        if (request.getUserId() <= 0) {
            return Optional.of(new CoreError("UserID", "Must not be empty or negative"));
        }
        if (usersDatabase.getUserById(request.getUserId()).isEmpty()) {
            return Optional.of(new CoreError("UserID", "User with this ID does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateQuantity(AddProductToBasketRequest request) {
        return (request.getQuantity() <= 0 || request.getQuantity() == null)
                ? Optional.of(new CoreError("Price", "Must be more than 0 and not empty "))
                : Optional.empty();
    }
}
