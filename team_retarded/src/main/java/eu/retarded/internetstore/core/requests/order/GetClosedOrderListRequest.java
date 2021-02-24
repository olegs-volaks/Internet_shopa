package eu.retarded.internetstore.core.requests.order;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetClosedOrderListRequest {
    private Pageable pageable;
    private  Long Id;
}
