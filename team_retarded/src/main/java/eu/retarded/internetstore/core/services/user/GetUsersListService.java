package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.GetUsersListRequest;
import eu.retarded.internetstore.core.responses.user.GetUsersListResponse;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class GetUsersListService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Validator validator;


    @Transactional
    public GetUsersListResponse execute(GetUsersListRequest request) {
        Set<ConstraintViolation<GetUsersListRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetUsersListResponse(errors, null);
        }

        Page <User> users = userRepository.findAll(request.getPageable());


        return new GetUsersListResponse(null, users);
    }
}