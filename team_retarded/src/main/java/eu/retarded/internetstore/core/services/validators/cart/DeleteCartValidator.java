package eu.retarded.internetstore.core.services.validators.cart;

import eu.retarded.internetstore.core.requests.cart.DeleteCartRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.cart.CartDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class DeleteCartValidator {

    @Autowired
    private CartDatabase cartDatabase;

    public List<CoreError> validate(DeleteCartRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteCartRequest request) {
        if (request.getDeleteCartId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty,negative or fractional"));
        }
        if (!cartDatabase.isExist(request.getDeleteCartId())) {
            return Optional.of(new CoreError("ID", "ID with the given id does not exist"));
        }
        return Optional.empty();
    }

}
