package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.GetUserListRequest;
import eu.retarded.internetstore.core.responses.user.GetUserListResponse;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class GetUserListService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public GetUserListResponse execute(GetUserListRequest request) {
        Set<ConstraintViolation<GetUserListRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetUserListResponse(errors);
        }
        if (request.getPageable() == null) {
            return new GetUserListResponse(null, userRepository.findAll());
        }
        Page<User> userPage = userRepository.findAll(request.getPageable());
        return new GetUserListResponse(userPage, userPage.toList());
    }
}
