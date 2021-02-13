package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.GetUserByIdRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GetUserByIdResponse extends CoreResponse<GetUserByIdRequest> {

    private User user;

    public GetUserByIdResponse(Set<ConstraintViolation<GetUserByIdRequest>> errors) {
        super(errors);
    }

    public GetUserByIdResponse(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
