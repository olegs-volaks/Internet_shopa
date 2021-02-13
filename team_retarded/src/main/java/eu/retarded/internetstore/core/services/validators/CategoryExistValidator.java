package eu.retarded.internetstore.core.services.validators;

import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class CategoryExistValidator implements ConstraintValidator<CategoryExist, Long> {

    @Autowired
    private CategoriesDatabase categoriesDatabase;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value != null && categoriesDatabase.isExist(value);
    }
}
