package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.GetByIdOrderRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.order.GetByIdOrderResponse;
import eu.retarded.internetstore.core.services.validators.order.GetByIdOrderValidator;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetByIdOrderService {

    @Autowired
    OrderDatabase orderDatabase;

    @Autowired
    GetByIdOrderValidator validator;

    public GetByIdOrderResponse execute(GetByIdOrderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetByIdOrderResponse(errors);
        }
        return new GetByIdOrderResponse(orderDatabase.getById(request.getId()).get());
    }
}
