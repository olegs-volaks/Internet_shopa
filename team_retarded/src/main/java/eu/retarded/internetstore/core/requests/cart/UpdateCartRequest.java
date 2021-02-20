package eu.retarded.internetstore.core.requests.cart;


import eu.retarded.internetstore.core.services.validators.CartExist;
import eu.retarded.internetstore.core.services.validators.ProductExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Getter
@RequiredArgsConstructor
public class UpdateCartRequest {

    @CartExist
    private final long id;

    @ProductExist
    private final long productId;

    @PositiveOrZero(message = "Must be positive or 0")
    private final int count;

}
