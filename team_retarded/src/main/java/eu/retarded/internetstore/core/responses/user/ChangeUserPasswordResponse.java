package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.requests.user.ChangeUserPasswordRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ChangeUserPasswordResponse extends CoreResponse<ChangeUserPasswordRequest> {

    private Long userId;

    public ChangeUserPasswordResponse(Set<ConstraintViolation<ChangeUserPasswordRequest>> errors) {
        super(errors);
    }

    public ChangeUserPasswordResponse(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
