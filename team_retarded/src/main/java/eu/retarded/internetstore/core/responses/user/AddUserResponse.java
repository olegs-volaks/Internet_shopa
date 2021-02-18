package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class AddUserResponse extends CoreResponse<AddUserRequest> {
    private User user;

    public AddUserResponse(User user) {
        this.user = user;
    }

    public AddUserResponse(Set<ConstraintViolation<AddUserRequest>> errors) {
        super(errors);
    }

    public User getUser() {
        return user;
    }
}
