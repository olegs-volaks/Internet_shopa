package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.RegisterUserRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class RegisterUserResponse extends CoreResponse<RegisterUserRequest> {

    private User user;

    public RegisterUserResponse(User user) {
        this.user = user;
    }

    public RegisterUserResponse(Set<ConstraintViolation<RegisterUserRequest>> errors) {
        super(errors);
    }

    public User getUser() {
        return user;
    }
}
