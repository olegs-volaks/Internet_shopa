package eu.retarded.internetstore.core.services.validators.cart;

import eu.retarded.internetstore.core.requests.cart.UpdateCartRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.cart.CartDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateCartValidator {

    @Autowired
    private CartDatabase cartDatabase;
    @Autowired
    private UsersDatabase usersDatabase;

    public List<CoreError> validate(UpdateCartRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateUserId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(UpdateCartRequest request) {
        if (request.getId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        if (!cartDatabase.isExist(request.getId())) {
            return Optional.of(new CoreError("ID", "ID with the given id does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserId(UpdateCartRequest request) {
        if (request.getUser_id() <= 0) {
            return Optional.of(new CoreError("UserId", "Must not be empty or negative"));
        }
        if (!usersDatabase.isExist(request.getUser_id())) {
            return Optional.of(new CoreError("UserId", "UserId with the given id does not exist"));
        }
        return Optional.empty();
    }
}
