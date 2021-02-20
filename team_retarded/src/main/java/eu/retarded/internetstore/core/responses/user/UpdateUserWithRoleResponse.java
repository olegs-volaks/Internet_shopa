package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.UpdateUserWithRoleRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateUserWithRoleResponse extends CoreResponse<UpdateUserWithRoleRequest> {

    private User user;

    public User getUserId() {
        return user;
    }

    public UpdateUserWithRoleResponse(Set<ConstraintViolation<UpdateUserWithRoleRequest>> errors) {
        super(errors);
    }

    public UpdateUserWithRoleResponse(User user) {
        this.user = user;
    }
}

