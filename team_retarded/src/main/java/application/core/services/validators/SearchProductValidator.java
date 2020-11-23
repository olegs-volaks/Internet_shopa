package application.core.services.validators;

import application.core.requests.SearchProductRequest;
import application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchProductValidator {

    public List<CoreError> validate(SearchProductRequest request) {
        List<CoreError> errors = new ArrayList<>();
        //todo: Добавить логику валидации
        return errors;
    }
}
