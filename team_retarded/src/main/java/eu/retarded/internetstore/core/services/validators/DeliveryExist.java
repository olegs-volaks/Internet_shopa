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
@Constraint(validatedBy = DeliveryExistValidator.class)
@Documented
public @interface DeliveryExist {
    String message() default "Delivery does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
