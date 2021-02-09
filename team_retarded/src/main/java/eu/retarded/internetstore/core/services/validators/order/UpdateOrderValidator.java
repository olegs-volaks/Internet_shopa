package eu.retarded.internetstore.core.services.validators.order;

import eu.retarded.internetstore.core.requests.order.UpdateOrderRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateOrderValidator {
    @Autowired
    private OrderDatabase orderDatabase;

    public List<CoreError> validate(UpdateOrderRequest request) {
        List<CoreError> errors = new ArrayList<>();

        return errors;
    }
}
