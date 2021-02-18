package eu.retarded.internetstore.core.requests.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@RequiredArgsConstructor
public class GetOrderListRequest {

    private final Pageable pageable;
}
