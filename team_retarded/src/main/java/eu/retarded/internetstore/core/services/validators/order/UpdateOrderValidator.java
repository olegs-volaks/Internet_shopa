package eu.retarded.internetstore.core.services.validators.order;

import eu.retarded.internetstore.core.requests.order.UpdateOrderRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.cart.CartDatabase;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import eu.retarded.internetstore.database.order.OrderDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateOrderValidator {
    @Autowired
    private OrderDatabase orderDatabase;


    @Autowired
    DeliveryDatabase deliveryDatabase;

    @Autowired
    UsersDatabase userDatabase;

    @Autowired
    CartDatabase cartDatabase;

    public List<CoreError> validate(UpdateOrderRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateOrderID(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validatePrice(request).ifPresent(errors::add);
        validateAddress(request).ifPresent(errors::add);
        validateDeliveryID(request).ifPresent(errors::add);
        validateUserId(request).ifPresent(errors::add);
        validateCartId(request).ifPresent(errors::add);
        return errors;
    }
    private Optional<CoreError> validateOrderID(UpdateOrderRequest request) {
        if (request.getId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        if (!orderDatabase.isExist(request.getId())) {
            return Optional.of(new CoreError("ID","The order with the given id does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateName(UpdateOrderRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return Optional.of(new CoreError("Name", "Must not be empty"));
        }
        if (request.getName().length() < 3 || request.getName().length() > 100) {
            return Optional.of(new CoreError("Name", "Must be between 3 and 100 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateSurname(UpdateOrderRequest request) {
        if (request.getSurname() == null || request.getSurname().isEmpty()) {
            return Optional.of(new CoreError("Region", "Must not be empty"));
        }
        if (request.getSurname().length() < 3 || request.getSurname().length() > 100) {
            return Optional.of(new CoreError("Surname", "Must be between 3 and 100 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateAddress(UpdateOrderRequest request) {
        if (request.getAddress() == null || request.getAddress().isEmpty()) {
            return Optional.of(new CoreError("Address", "Must not be empty"));
        }
        if (request.getSurname().length() < 10 || request.getSurname().length() > 200) {
            return Optional.of(new CoreError("Address", "Must be between 10 and 200 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDeliveryID(UpdateOrderRequest request) {
        if (request.getDeliveryId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        if (!deliveryDatabase.isExist(request.getDeliveryId())) {
            return Optional.of(new CoreError("ID","The delivery with the given id does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateUserId(UpdateOrderRequest request) {
        if (request.getUserId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        if (!userDatabase.isExist(request.getUserId())) {
            return Optional.of(new CoreError("ID","The user with the given id does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateCartId(UpdateOrderRequest request) {
        if (request.getCartId() <= 0) {
            return Optional.of(new CoreError("ID", "Must not be empty or negative"));
        }
        if (!cartDatabase.isExist(request.getCartId())) {
            return Optional.of(new CoreError("ID","The cart with the given id does not exist"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validatePrice(UpdateOrderRequest request) {
        return (request.getTotalPrice() <= 0 || request.getTotalPrice() > 1000000000)
                ? Optional.of(new CoreError("Price", "Must be between 0 and 1000000000"))
                : Optional.empty();
    }
}

