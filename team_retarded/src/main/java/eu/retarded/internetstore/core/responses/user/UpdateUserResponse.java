package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.requests.user.UpdateUserRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateUserResponse extends CoreResponse<UpdateUserRequest> {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public UpdateUserResponse(Set<ConstraintViolation<UpdateUserRequest>> errors) {
        super(errors);
    }

    public UpdateUserResponse(Long userId) {
        this.userId = userId;
    }
}
