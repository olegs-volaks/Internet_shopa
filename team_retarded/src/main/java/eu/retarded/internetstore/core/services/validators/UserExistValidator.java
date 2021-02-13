package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserExistValidator implements ConstraintValidator<CartExist, Long> {

    @Autowired
    private UsersDatabase usersDatabase;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && usersDatabase.isExist(value);
    }
}
