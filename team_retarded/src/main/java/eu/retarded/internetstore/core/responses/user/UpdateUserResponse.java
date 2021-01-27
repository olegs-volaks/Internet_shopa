package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class UpdateUserResponse extends CoreResponse {

    private long userId;

    public long getUserId() {
        return userId;
    }

    public UpdateUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateUserResponse(long userId) {
        this.userId = userId;
    }
}
