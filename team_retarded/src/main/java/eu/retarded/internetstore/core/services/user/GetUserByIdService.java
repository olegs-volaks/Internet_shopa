package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.requests.user.GetUserByIdRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.user.GetUserByIdResponse;
import eu.retarded.internetstore.core.services.validators.user.GetUserByIdValidator;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetUserByIdService {

    @Autowired
    private UsersDatabase usersDatabase;

    @Autowired
    private GetUserByIdValidator validator;

    @Transactional
    public GetUserByIdResponse execute(GetUserByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetUserByIdResponse(errors);
        }
        return new GetUserByIdResponse(usersDatabase.getUserById(request.getUserId()).get());
    }
}
