package eu.retarded.internetstore.core.services.validators.order;

import eu.retarded.internetstore.core.requests.order.GetByIdOrderRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetByIdOrderValidator {
    @Autowired
    OrderDatabase orderDatabase;

    public List<CoreError> validate(GetByIdOrderRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetByIdOrderRequest request) {
        if (request.getId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        if (!orderDatabase.isExist(request.getId())) {
            return Optional.of(new CoreError("ID","The delivery with the given id does not exist"));
        }
        return Optional.empty();
    }
}
