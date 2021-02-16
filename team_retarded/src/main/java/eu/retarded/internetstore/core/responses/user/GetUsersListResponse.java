package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.GetUsersListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GetUsersListResponse extends CoreResponse<GetUsersListRequest> {

    private Page<User> users;

    public GetUsersListResponse(Set<ConstraintViolation<GetUsersListRequest>> errors, Page <User> users) {
        super(errors);
        this.users = users;
    }

    public Page<User> getUsers() {
        return users;
    }
}
