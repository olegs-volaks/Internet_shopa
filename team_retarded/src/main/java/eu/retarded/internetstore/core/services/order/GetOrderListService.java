package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.GetOrderListRequest;
import eu.retarded.internetstore.core.responses.order.GetOrderListPagingResponse;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class GetOrderListService {

    @Autowired
    private OrderDatabase orderDatabase;

    @Transactional
    public GetOrderListPagingResponse execute(GetOrderListRequest request) {
        return new GetOrderListPagingResponse(null, orderDatabase.getList());
    }
}
