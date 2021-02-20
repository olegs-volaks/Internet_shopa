package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserExistValidator implements ConstraintValidator<UserExist, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && userRepository.existsById(value);
    }
}
