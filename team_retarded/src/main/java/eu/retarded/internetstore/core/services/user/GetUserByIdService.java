package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.requests.user.GetUserByIdRequest;
import eu.retarded.internetstore.core.responses.user.GetUserByIdResponse;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class GetUserByIdService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public GetUserByIdResponse execute(GetUserByIdRequest request) {
        Set<ConstraintViolation<GetUserByIdRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetUserByIdResponse(errors);
        }
        return new GetUserByIdResponse(userRepository.getOne(request.getUserId()));
    }
}
