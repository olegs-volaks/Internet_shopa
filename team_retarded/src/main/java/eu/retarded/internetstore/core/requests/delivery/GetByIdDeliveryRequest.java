package eu.retarded.internetstore.core.requests.delivery;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class GetByIdDeliveryRequest {

    private final Long id;

}
