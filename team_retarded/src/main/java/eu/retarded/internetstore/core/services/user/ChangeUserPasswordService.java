package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.ChangeUserPasswordRequest;
import eu.retarded.internetstore.core.responses.user.ChangeUserPasswordResponse;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ChangeUserPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ChangeUserPasswordResponse execute(ChangeUserPasswordRequest request) {
        Set<ConstraintViolation<ChangeUserPasswordRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ChangeUserPasswordResponse(errors);
        }
        User user = userRepository.getOne(request.getUserId());
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return new ChangeUserPasswordResponse(user);
    }
}
