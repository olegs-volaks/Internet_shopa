package eu.retarded.internetstore.core.services.validators.order;

import eu.retarded.internetstore.core.requests.order.GetOrderListPagingRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GetOrderListPagingValidator {

    public List<CoreError> validate(GetOrderListPagingRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePage(request).ifPresent(errors::add);
        return errors;
    }


    private Optional<CoreError> validatePage(GetOrderListPagingRequest request) {
        if (request.getPage() <= 0) {
            return Optional.of(new CoreError("page", "Must be greater then 0!"));
        }
        return Optional.empty();
    }
}
