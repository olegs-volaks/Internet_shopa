package application.core.services.validators;

import application.core.requests.SearchProductRequest;
import application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchProductValidator {

    public List<CoreError> validate(SearchProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateName(request).ifPresent(errors::add);
        validateDescription(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateName(SearchProductRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return Optional.of(new CoreError("Name","Must not be empty!"));
        }
        if (request.getName().length() < 4 || request.getName().length() > 100) {
            return Optional.of(new CoreError("Name","Must be between 4 and 100 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDescription(SearchProductRequest request) {
        if (request.getDescription() == null || request.getDescription().isEmpty()) {
            return Optional.of(new CoreError("Description","Must not be empty!"));
        }
        if (request.getDescription().length() < 10 || request.getDescription().length() > 2000) {
            return Optional.of(new CoreError("Description","Must be between 10 and 2000 characters"));
        }
        return Optional.empty();
    }
}
