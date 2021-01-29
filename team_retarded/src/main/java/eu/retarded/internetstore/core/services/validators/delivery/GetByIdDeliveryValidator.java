package eu.retarded.internetstore.core.services.validators.delivery;

import eu.retarded.internetstore.core.requests.delivery.GetByIdDeliveryRequest;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetByIdDeliveryValidator {

    @Autowired
    DeliveryDatabase deliveryDatabase;

    public List<CoreError> validate(GetByIdDeliveryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(GetByIdDeliveryRequest request) {
        if (request.getId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        return Optional.empty();
    }
}
