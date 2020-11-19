package application.core.services.validators;

import application.core.requests.FilterProductsByNameRequest;
import application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilterProductsByNameValidator {
    public List<CoreError> validate(FilterProductsByNameRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName1(request).ifPresent(errors::add);
        validateName2(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName1(FilterProductsByNameRequest request) {
        if (request.getName1() == null || request.getName1().isEmpty()) {
            return Optional.of(new CoreError("Name", "Must not be empty!"));
        }

        if (request.getName1().length() < 4 || request.getName1().length() > 100) {
            return Optional.of(new CoreError("Name", "Must be between 4 and 100 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateName2(FilterProductsByNameRequest request) {
        if (request.getName2() == null || request.getName2().isEmpty()) {
            return Optional.of(new CoreError("Name", "Must not be empty!"));
        }

        if (request.getName2().length() < 4 || request.getName2().length() > 100) {
            return Optional.of(new CoreError("Name", "Must be between 4 and 100 characters"));
        }
        return Optional.empty();
    }
}
