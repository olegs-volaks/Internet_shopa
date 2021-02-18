package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.requests.user.DeleteUserRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class DeleteUserResponse extends CoreResponse<DeleteUserRequest> {

    private boolean isDeleted;

    public DeleteUserResponse(Set<ConstraintViolation<DeleteUserRequest>> errors) {
        super(errors);
    }

    public DeleteUserResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
