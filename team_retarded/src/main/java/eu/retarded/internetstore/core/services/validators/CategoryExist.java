package eu.retarded.internetstore.core.services.validators;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = CategoryExistValidator.class)
@Documented
public @interface CategoryExist {
    String message() default "Category does not exist";
}
