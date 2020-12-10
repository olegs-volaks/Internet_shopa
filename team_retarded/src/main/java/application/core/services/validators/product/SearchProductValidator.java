package application.core.services.validators.product;

import application.core.requests.product.Ordering;
import application.core.requests.product.Paging;
import application.core.requests.product.SearchProductRequest;
import application.core.responses.CoreError;
import com.retarded.di.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class SearchProductValidator {

    public List<CoreError> validate(SearchProductRequest request) {
        List<CoreError> errors = new ArrayList<>(validateSearchFields(request));

        if (request.getOrdering() != null) {
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }
        if (request.getPaging() != null) {
            validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
            validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
        }

        return errors;
    }


    private List<CoreError> validateSearchFields(SearchProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getName()) && isEmpty(request.getDescription())) {
            errors.add(new CoreError("name",        "Must not be empty!"));
            errors.add(new CoreError("description", "Must not be empty!"));
        }
        return errors;
    }
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageNumber(Paging paging) {
        if ((paging.getPageNumber() == null && paging.getPageSize() == null)) {
            return Optional.empty();
        } else if (paging.getPageNumber() == null && paging.getPageSize() != null) {
            return Optional.of(new CoreError("pageNumber", "Must not be empty!"));
        } else if (paging.getPageNumber() <= 0) {
            return Optional.of(new CoreError("pageNumber", "Must be greater then 0!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(Paging paging) {
        if ((paging.getPageNumber() == null && paging.getPageSize() == null)) {
            return Optional.empty();
        } else if (paging.getPageSize() == null && paging.getPageNumber() != null) {
            return Optional.of(new CoreError("pageSize", "Must not be empty!"));
        } else if (paging.getPageSize() <= 0) {
            return Optional.of(new CoreError("pageSize", "Must be greater then 0!"));
        }
        return Optional.empty();
    }

}
