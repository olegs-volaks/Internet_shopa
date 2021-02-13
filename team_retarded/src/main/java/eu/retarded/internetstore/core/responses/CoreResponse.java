package eu.retarded.internetstore.core.responses;

import javax.validation.ConstraintViolation;
import java.util.Set;

public abstract class CoreResponse<T> {

    private Set<ConstraintViolation<T>> errors;

    public CoreResponse() {
    }

    public Set<ConstraintViolation<T>> getErrors() {
        return errors;
    }

    public CoreResponse(Set<ConstraintViolation<T>> errors) {
        this.errors = errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
