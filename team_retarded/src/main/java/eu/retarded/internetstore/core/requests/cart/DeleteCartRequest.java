package eu.retarded.internetstore.core.requests.cart;

import eu.retarded.internetstore.core.services.validators.CartExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteCartRequest {

    @CartExist
    private final Long deleteCartId;
}
