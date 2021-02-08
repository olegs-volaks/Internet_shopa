package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchProductValidator {

    public List<CoreError> validate(SearchProductRequest request) {
        List<CoreError> errors = new ArrayList<>(validateSearchFields(request));

        if (request.getSorting() != null) {
            validateMandatoryOrderDirection(request.getSorting()).ifPresent(errors::add);
        }
        return errors;
    }


    private List<CoreError> validateSearchFields(SearchProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getKeyWord())) {
            errors.add(new CoreError("name", "Must not be empty!"));
            errors.add(new CoreError("description", "Must not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(String sorting) {
        return (!sorting.equals("DESC") && !sorting.equals("ASC"))
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }
}
