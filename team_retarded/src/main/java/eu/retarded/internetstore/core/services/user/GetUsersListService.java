package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.requests.user.GetUsersListRequest;
import eu.retarded.internetstore.core.responses.user.GetUsersListResponse;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetUsersListService {

    @Autowired
    private UsersDatabase usersDatabase;

    @Transactional
    public GetUsersListResponse execute(GetUsersListRequest request) {
        return new GetUsersListResponse(null, usersDatabase.getList());
    }
}
