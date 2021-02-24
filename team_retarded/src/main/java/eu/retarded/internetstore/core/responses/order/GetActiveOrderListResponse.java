package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetActiveOrderListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class GetActiveOrderListResponse extends CoreResponse<GetActiveOrderListRequest> {

    private Page<Order> ordersPage;
    private List<Order> ordersList;

    public GetActiveOrderListResponse(Set<ConstraintViolation<GetActiveOrderListRequest>> errors) {
        super(errors);
    }

    public GetActiveOrderListResponse(Page<Order> ordersPage, List<Order> ordersList) {
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
