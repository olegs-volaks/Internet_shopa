package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class DeleteUserResponse extends CoreResponse {

    private boolean isUserDeleted;

    public DeleteUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteUserResponse(boolean isUserDeleted) {
        this.isUserDeleted = isUserDeleted;
    }

    public boolean isUserDeleted() {
        return isUserDeleted;
    }
}
