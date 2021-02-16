package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class DeliveryExistValidator implements ConstraintValidator<DeliveryExist, Long> {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && deliveryRepository.existsById(value);
    }
}
