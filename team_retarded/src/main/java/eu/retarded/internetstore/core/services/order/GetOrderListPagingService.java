package eu.retarded.internetstore.core.services.order;


import eu.retarded.internetstore.core.requests.order.GetOrderListPagingRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.order.GetOrderListPagingResponse;
import eu.retarded.internetstore.core.services.validators.order.GetOrderListPagingValidator;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class GetOrderListPagingService {
    @Autowired
    OrderDatabase orderDatabase ;
    @Autowired
    GetOrderListPagingValidator validator;

    @Transactional
    public GetOrderListPagingResponse execute(GetOrderListPagingRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetOrderListPagingResponse(errors,null);
        }
        return new GetOrderListPagingResponse(null,orderDatabase.getListWithPaging(request.getPage()));
    }
}
