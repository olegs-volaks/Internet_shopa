package eu.retarded.internetstore.core.requests.cart;


import eu.retarded.internetstore.core.services.validators.UserExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddCartRequest {

    @UserExist
    private final Long userId;
}
