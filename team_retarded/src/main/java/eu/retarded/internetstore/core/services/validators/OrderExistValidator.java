package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrderExistValidator implements ConstraintValidator <CartExist, Long> {

@Autowired
private OrderDatabase orderDatabase;

@Override
public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && orderDatabase.isExist(value);
        }
}
