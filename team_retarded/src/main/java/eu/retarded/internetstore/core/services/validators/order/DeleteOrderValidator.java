package eu.retarded.internetstore.core.services.validators.order;

import eu.retarded.internetstore.core.requests.order.DeleteOrderRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteOrderValidator {
    @Autowired
    private OrderDatabase orderDatabase;

    public List<CoreError> validate(DeleteOrderRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteOrderRequest request) {
        if (request.getDeleteOrderId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty,negative or fractional"));
        }
        if (!orderDatabase.isExist(request.getDeleteOrderId())) {
            return Optional.of(new CoreError("ID","The delivery with the given id does not exist"));
        }
        return Optional.empty();
    }
}