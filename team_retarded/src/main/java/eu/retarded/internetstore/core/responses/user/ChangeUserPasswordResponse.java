package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class ChangeUserPasswordResponse extends CoreResponse {

    private Long userId;

    public ChangeUserPasswordResponse(List<CoreError> errors) {
        super(errors);
    }

    public ChangeUserPasswordResponse(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
