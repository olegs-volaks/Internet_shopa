package application.core.services.validators;

import application.core.requests.SearchProductRequest;
import application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchProductValidator {

    public List<CoreError> validate(SearchProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateNameAndDescription(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateNameAndDescription(SearchProductRequest request) {
        if ((request.getName() == null || request.getName().isEmpty())&&
        (request.getDescription() == null || request.getDescription().isEmpty())) {
            return Optional.of(new CoreError("Name,Description","Both must be not empty!"));
        }
        return Optional.empty();
    }
}
