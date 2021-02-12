package eu.retarded.internetstore.core.services.validators;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ProductExistValidator.class)
@Documented
public @interface ProductExist {
    String message() default "Product does not exist";
}
