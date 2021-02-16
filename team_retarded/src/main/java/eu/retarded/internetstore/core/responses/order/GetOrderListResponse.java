package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetOrderListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GetOrderListResponse extends CoreResponse<GetOrderListRequest> {

    private Page<Order> orders;

    public GetOrderListResponse(Set<ConstraintViolation<GetOrderListRequest>> errors, Page<Order> orders) {
        super(errors);
        this.orders = orders;
    }

    public Page<Order> getOrders() {
        return orders;
    }
}
