package eu.retarded.internetstore.core.requests.user;

import eu.retarded.internetstore.core.services.validators.ProductExist;
import eu.retarded.internetstore.core.services.validators.UserExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Getter
@RequiredArgsConstructor
public class AddProductToUserCartRequest {


    @UserExist
    private final long userId;

    @ProductExist
    private final long productId;

    @PositiveOrZero(message = "Must be positive or 0")
    private final int count;
}
