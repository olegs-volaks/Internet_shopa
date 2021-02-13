package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class AddUserResponse extends CoreResponse<AddUserRequest> {

    private Long userId;

    public AddUserResponse(Long userId) {
        this.userId = userId;
    }

    public AddUserResponse(Set<ConstraintViolation<AddUserRequest>> errors) {
        super(errors);
    }

    public Long getUserId() {
        return userId;
    }
}
