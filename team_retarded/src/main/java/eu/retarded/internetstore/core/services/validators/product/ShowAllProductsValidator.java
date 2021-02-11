package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShowAllProductsValidator {
    public List<CoreError> validate(ShowAllProductsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        return errors;
    }

}
