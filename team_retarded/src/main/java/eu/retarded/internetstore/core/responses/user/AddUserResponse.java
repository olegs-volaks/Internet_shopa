package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class AddUserResponse extends CoreResponse {

    private long userId;

    public AddUserResponse(long userId) {
        this.userId = userId;
    }

    public AddUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public long getUserId() {
        return userId;
    }
}
