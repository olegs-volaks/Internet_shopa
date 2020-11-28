package application.core.services.validators;

import application.core.requests.Ordering;
import application.core.requests.Paging;
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

    private Optional<CoreError> validateName(SearchProductRequest request) {
        if (request.getName() == null && request.getDescription() == null) {
            return Optional.of(new CoreError("name", "Must be not empty!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateDescription(SearchProductRequest request) {
        if (request.getName() == null && request.getDescription() == null) {
            return Optional.of(new CoreError("description", "Must be not empty!"));
        }
        return Optional.empty();
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
        }else if(paging.getPageNumber() == null && paging.getPageSize() != null){
            return Optional.of(new CoreError("pageNumber", "Must not be empty!"));
        }else if ( paging.getPageNumber() <= 0){
            return Optional.of(new CoreError("pageNumber", "Must be greater then 0!"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateMandatoryPageSize(Paging paging) {
        if ((paging.getPageNumber() == null && paging.getPageSize() == null)){
            return Optional.empty();
        } else if (paging.getPageSize() == null && paging.getPageNumber() != null){
            return Optional.of(new CoreError("pageSize", "Must not be empty!"));
        }else if ( paging.getPageSize() <= 0){
            return Optional.of(new CoreError("pageSize", "Must be greater then 0!"));
        }
        return Optional.empty();
    }

}
