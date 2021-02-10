package eu.retarded.internetstore.core.requests.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateOrderRequest {

    private final Long id;
    private final String name;
    private final String surname;
    private final String address;
    private final long cartId;
    private final long deliveryId;
    private final long userId;
    private final double totalPrice;
    private final int status;

}
