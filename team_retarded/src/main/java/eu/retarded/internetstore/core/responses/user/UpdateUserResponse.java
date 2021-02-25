package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.UpdateUserRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateUserResponse extends CoreResponse<UpdateUserRequest> {

    private User user;

    public User getUser() {
        return user;
    }

    public UpdateUserResponse(Set<ConstraintViolation<UpdateUserRequest>> errors) {
        super(errors);
    }

    public UpdateUserResponse(User user) {
        this.user = user;
    }
}
