package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetOrderListPagingRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class GetOrderListPagingResponse extends CoreResponse<GetOrderListPagingRequest> {

    private List<Order> orders;

    public GetOrderListPagingResponse(Set<ConstraintViolation<GetOrderListPagingRequest>> errors, List<Order> orders) {
        super(errors);
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
