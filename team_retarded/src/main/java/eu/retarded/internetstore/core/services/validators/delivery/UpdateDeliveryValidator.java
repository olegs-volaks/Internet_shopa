package eu.retarded.internetstore.core.services.validators.delivery;

import eu.retarded.internetstore.core.requests.delivery.UpdateDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateDeliveryValidator {

    @Autowired
    private DeliveryDatabase deliveryDatabase;

    public List<CoreError> validate(UpdateDeliveryRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(errors::add);
        validateTitle(request).ifPresent(errors::add);
        validateRegion(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateId(UpdateDeliveryRequest request) {
        if (request.getId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        if (!deliveryDatabase.isExist(request.getId())) {
            return Optional.of(new CoreError("ID", "The delivery with the given id does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateTitle(UpdateDeliveryRequest request) {
        if (request.getTitle() == null || request.getTitle().isEmpty()) {
            return Optional.of(new CoreError("Title", "Must not be empty"));
        }
        if (request.getTitle().length() < 3 || request.getTitle().length() > 100) {
            return Optional.of(new CoreError("Title", "Must be between 3 and 100 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateRegion(UpdateDeliveryRequest request) {
        if (request.getRegion() == null || request.getRegion().isEmpty()) {
            return Optional.of(new CoreError("Region", "Must not be empty"));
        }
        if (request.getRegion().length() < 3 || request.getRegion().length() > 2000) {
            return Optional.of(new CoreError("Region", "Must be between 3 and 2000 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validatePrice(UpdateDeliveryRequest request) {
        return (request.getPrice() <= 0 || request.getPrice() > 1000000000)
                ? Optional.of(new CoreError("Price", "Must be between 0 and 1000000000"))
                : Optional.empty();
    }
}
