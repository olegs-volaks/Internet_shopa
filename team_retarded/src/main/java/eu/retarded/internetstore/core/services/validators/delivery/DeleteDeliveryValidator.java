package eu.retarded.internetstore.core.services.validators.delivery;

import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteDeliveryValidator {

    public List<CoreError> validate(DeleteDeliveryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(DeleteDeliveryRequest request) {
        if (request.getDeliveryIdToDelete() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty,negative or fractional"));
        }
        return Optional.empty();
    }
}
