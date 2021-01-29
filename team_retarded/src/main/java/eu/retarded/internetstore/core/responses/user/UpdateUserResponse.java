package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class UpdateUserResponse extends CoreResponse {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public UpdateUserResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateUserResponse(Long userId) {
        this.userId = userId;
    }
}
