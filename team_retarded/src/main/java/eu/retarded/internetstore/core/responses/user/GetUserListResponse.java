package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.GetUserListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class GetUserListResponse extends CoreResponse<GetUserListRequest> {

    private Page<User> page;
    private List<User> list;

    public GetUserListResponse(Set<ConstraintViolation<GetUserListRequest>> errors) {
        super(errors);
    }

    public GetUserListResponse(Page<User> page, List<User> list) {
        this.page = page;
        this.list = list;
    }

    public Page<User> getPage() {
        return page;
    }

    public List<User> getList() {
        return list;
    }
}
