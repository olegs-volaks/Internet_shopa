package eu.retarded.internetstore.core.requests.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;


@Getter
@RequiredArgsConstructor
public class GetDeliveryListRequest {

    private final Pageable pageable;
}
