package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class DeleteUserResponse extends CoreResponse {

    private boolean isDeleted;

    public DeleteUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteUserResponse(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
