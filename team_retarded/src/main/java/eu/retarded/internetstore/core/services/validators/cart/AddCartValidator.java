package eu.retarded.internetstore.core.services.validators.cart;

import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.cart.CartDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddCartValidator {

    @Autowired CartDatabase cartDatabase;

    public List<CoreError> validate(AddCartRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateUserId(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validateUserId(AddCartRequest request) {
        if (request.getUserId() <= 0) {
            return Optional.of(new CoreError("UserId","Must not be empty or negative"));
        }
        if (!cartDatabase.isExist(request.getUserId())) {
            return Optional.of(new CoreError("UserId","User with the given id does not exist"));
        }
        return Optional.empty();
    }
}
