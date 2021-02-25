package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrderExistValidator implements ConstraintValidator<CartExist, Long> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && orderRepository.existsById(value);
    }
}
