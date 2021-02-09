package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.GetOrderListRequest;
import eu.retarded.internetstore.core.responses.order.GetOrderListResponse;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class GetOrderListService {

    @Autowired
    OrderDatabase orderDatabase ;

    @Transactional
    public GetOrderListResponse execute(GetOrderListRequest request) {
        return new GetOrderListResponse(null,orderDatabase.getList());
    }
}
