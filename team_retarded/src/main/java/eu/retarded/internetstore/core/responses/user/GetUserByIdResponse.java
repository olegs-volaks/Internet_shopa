package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class GetUserByIdResponse extends CoreResponse {

    private User user;

    public GetUserByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetUserByIdResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
