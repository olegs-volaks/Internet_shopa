package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.GetUsersListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class GetUsersListResponse extends CoreResponse<GetUsersListRequest> {

    private List<User> users;

    public GetUsersListResponse(Set<ConstraintViolation<GetUsersListRequest>> errors, List<User> users) {
        super(errors);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
