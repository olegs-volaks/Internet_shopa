package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class GetUsersListResponse extends CoreResponse {

    private List<User> users;

    public GetUsersListResponse(List<CoreError> errors, List<User> users) {
        super(errors);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
