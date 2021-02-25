package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.ChangeUserPasswordRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ChangeUserPasswordResponse extends CoreResponse<ChangeUserPasswordRequest> {

    private User user;

    public User getUser() {
        return user;
    }

    public ChangeUserPasswordResponse(Set<ConstraintViolation<ChangeUserPasswordRequest>> errors) {
        super(errors);
    }

    public ChangeUserPasswordResponse(User user) {
        this.user = user;
    }
}
