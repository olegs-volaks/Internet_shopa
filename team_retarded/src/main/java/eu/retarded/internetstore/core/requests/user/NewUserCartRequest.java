package eu.retarded.internetstore.core.requests.user;

import eu.retarded.internetstore.core.services.validators.UserExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NewUserCartRequest {

    @UserExist
    private final long userId;
}
