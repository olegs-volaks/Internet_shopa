package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.GetOrderListPagingRequest;
import eu.retarded.internetstore.core.responses.order.GetOrderListPagingResponse;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class GetOrderListPagingService {

    @Autowired
    private OrderDatabase orderDatabase;

    @Autowired
    private Validator validator;

    @Transactional
    public GetOrderListPagingResponse execute(GetOrderListPagingRequest request) {
        Set<ConstraintViolation<GetOrderListPagingRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetOrderListPagingResponse(errors, null);
        }
        return new GetOrderListPagingResponse(null, orderDatabase.getListWithPaging(request.getPage()));
    }
}
