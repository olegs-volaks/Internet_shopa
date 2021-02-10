package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class GetOrderListPagingResponse extends CoreResponse {

    private List<Order> orders;

    public GetOrderListPagingResponse(List<CoreError> errors, List<Order> orders) {
        super(errors);
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
