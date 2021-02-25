package eu.retarded.internetstore.core.requests.order;


import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class GetActiveOrderListRequest {

    private Long Id;
    private Pageable pageable;

    public GetActiveOrderListRequest(Long id) {
        Id = id;
    }

    public GetActiveOrderListRequest(Long id, Pageable pageable) {
        Id = id;
        this.pageable = pageable;
    }
}
