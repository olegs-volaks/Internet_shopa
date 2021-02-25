package eu.retarded.internetstore.core.requests.order;


import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class GetClosedOrderListRequest {

    private Long Id;
    private Pageable pageable;

    public GetClosedOrderListRequest(Long id, Pageable pageable) {
        Id = id;
        this.pageable = pageable;
    }

    public GetClosedOrderListRequest(Long id) {
        Id = id;
    }
}
