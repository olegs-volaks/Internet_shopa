package application.core.services.validators;

import application.core.requests.FilterProductsByPriceRequest;
import application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilterProductsByPriceValidator {
    public List<CoreError> validate(FilterProductsByPriceRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePriceMin(request).ifPresent(errors::add);
        validatePriceMax(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePriceMin(FilterProductsByPriceRequest request) {
        if (request.getPriceMin() <= 0 ||
                (request.getPriceMin() % 2 != 1 && request.getPriceMin() % 2 != 0)) {
            return Optional.of(new CoreError("minimum price", "Must not be empty or negative"));
        }

        return Optional.empty();
    }

    private Optional<CoreError> validatePriceMax(FilterProductsByPriceRequest request) {
        if (request.getPriceMax() <= 0 || request.getPriceMin() >= request.getPriceMax()) {
            return Optional.of(new CoreError("maximum price", "Must not be empty, negative or less than minimum price"));
        }
        return Optional.empty();
    }

}

