package application.core.services.validators.product;

import application.core.requests.product.SearchProductRequest;
import application.core.responses.product.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchProductValidator {

    public List<CoreError> validate(SearchProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        //todo: Добавить логику валидации
        return errors;
    }
}
