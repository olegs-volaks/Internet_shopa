package eu.retarded.internetstore.core.requests.cart;


import eu.retarded.internetstore.core.services.validators.CartExist;
import eu.retarded.internetstore.core.services.validators.UserExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateCartRequest {

    @CartExist
    private final long id;
    @UserExist
    private final long user_id;


}
