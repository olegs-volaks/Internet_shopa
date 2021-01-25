package eu.retarded.internetstore.core.services.validators.basket;

import eu.retarded.internetstore.core.requests.basket.DeleteProductFromBasketRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.product.ProductDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteProductFromBasketValidator {

    @Autowired
    private UsersDatabase usersDatabase;
    @Autowired
    private ProductDatabase productDatabase;

    public List<CoreError> validate(DeleteProductFromBasketRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateProductID(request).ifPresent(errors::add);
        validateUserID(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductID(DeleteProductFromBasketRequest request) {
        if (request.getProductId() <= 0) {
            return Optional.of(new CoreError("ProductID", "Must not be empty or negative"));
        }
        if (productDatabase.getById(request.getProductId()).isEmpty()) {
            return Optional.of(new CoreError("ProductID", "Product with this ID does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserID(DeleteProductFromBasketRequest request) {
        if (request.getUserId() <= 0) {
            return Optional.of(new CoreError("UserID", "Must not be empty or negative"));
        }
        if (usersDatabase.getUserById(request.getUserId()).isEmpty()) {
            return Optional.of(new CoreError("UserID", "User with this ID does not exist"));
        }
        return Optional.empty();
    }

}

