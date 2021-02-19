package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetOrderListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class GetOrderListResponse extends CoreResponse<GetOrderListRequest> {

    private Page<Order> ordersPage;
    private List<Order> ordersList;

    public GetOrderListResponse(Set<ConstraintViolation<GetOrderListRequest>> errors) {
        super(errors);
    }

    public GetOrderListResponse(Page<Order> ordersPage, List<Order> ordersList) {
        this.ordersPage = ordersPage;
        this.ordersList = ordersList;
    }

    public Page<Order> getOrdersPage() {
        return ordersPage;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }
}
