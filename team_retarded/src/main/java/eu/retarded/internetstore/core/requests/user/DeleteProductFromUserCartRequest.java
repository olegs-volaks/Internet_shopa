package eu.retarded.internetstore.core.requests.user;


import eu.retarded.internetstore.core.services.validators.ProductExist;
import eu.retarded.internetstore.core.services.validators.UserExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteProductFromUserCartRequest {

    @UserExist
    private final long userId;

    @ProductExist
    private final long productId;

}
