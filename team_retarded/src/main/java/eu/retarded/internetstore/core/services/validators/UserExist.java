package eu.retarded.internetstore.core.services.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UserExistValidator.class)
@Documented
public @interface UserExist {
    String message() default "The user does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
