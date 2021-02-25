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
@Constraint(validatedBy = OrderExistValidator.class)
@Documented
public @interface OrderExist {
    String message() default "The order does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
