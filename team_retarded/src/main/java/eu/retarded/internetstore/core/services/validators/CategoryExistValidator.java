package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class CategoryExistValidator implements ConstraintValidator<CategoryExist, Long> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && categoryRepository.existsById(value);
    }
}
