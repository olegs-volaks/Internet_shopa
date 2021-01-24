package eu.retarded.internetstore.core.services.validators.delivery;

import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddDeliveryValidator {

    public List<CoreError> validate(AddDeliveryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateTitle(request).ifPresent(errors::add);
        validateRegion(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateTitle(AddDeliveryRequest request) {
        if (request.getTitle() == null || request.getTitle().isEmpty()) {
            return Optional.of(new CoreError("Title", "Must not be empty"));
        }
        if (request.getTitle().length() < 3 || request.getTitle().length() > 100) {
            return Optional.of(new CoreError("Title", "Must be between 3 and 100 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateRegion(AddDeliveryRequest request) {
        if (request.getRegion() == null || request.getRegion().isEmpty()) {
            return Optional.of(new CoreError("Region", "Must not be empty"));
        }
        if (request.getRegion().length() < 10 || request.getRegion().length() > 2000) {
            return Optional.of(new CoreError("Region", "Must be between 10 and 2000 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validatePrice(AddDeliveryRequest request) {
        return (request.getPrice() <= 0 || request.getPrice() > 1000000000)
                ? Optional.of(new CoreError("Price", "Must be between 0 and 1000000000"))
                : Optional.empty();
    }
}
