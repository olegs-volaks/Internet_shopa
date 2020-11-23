package application.core.responses;

import application.items.Product;

import java.util.List;

public class CoreResponse {

    private List<CoreError> errors;
    private List <Product> productsByFilter;

    public CoreResponse() {
    }

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }
    public CoreResponse(List<CoreError> errors, List <Product> productsByFilter) {
        this.errors = errors;
        this.productsByFilter = productsByFilter;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
