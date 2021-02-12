package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class DeliveryExistValidator implements ConstraintValidator<DeliveryExist, Long> {

    @Autowired
    private DeliveryDatabase deliveryDatabase;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && deliveryDatabase.isExist(value);
    }
}
