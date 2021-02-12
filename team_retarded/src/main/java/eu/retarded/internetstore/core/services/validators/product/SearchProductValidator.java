package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchProductValidator {

    public List<CoreError> validate(SearchProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateIdKeyWord(request).ifPresent(errors::add);
        validateMandatoryOrderDirection(request).ifPresent(errors::add);
        validatePage(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateIdKeyWord(SearchProductRequest request) {
        if (isEmpty(request.getKeyWord())) {
            return Optional.of(new CoreError("KeyWord", "Must not be empty!"));
        }
        return Optional.empty();
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(SearchProductRequest request) {
        return (isEmpty(request.getSorting()))
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePage(SearchProductRequest request) {
        if (request.getPage() <= 0) {
            return Optional.of(new CoreError("page", "Must be greater then 0!"));
        }
        return Optional.empty();
    }
}
