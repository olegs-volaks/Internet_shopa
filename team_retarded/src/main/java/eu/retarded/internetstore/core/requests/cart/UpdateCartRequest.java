package eu.retarded.internetstore.core.requests.cart;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Getter
@RequiredArgsConstructor
public class UpdateCartRequest {


    private final long id;


    private final long productId;

    @PositiveOrZero(message = "Must be positive or 0")
    private final int count;

}
