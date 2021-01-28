package eu.retarded.internetstore.core.responses;

import java.util.Objects;

public class CoreError {

    private final String field;
    private final String message;

    public CoreError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreError coreError = (CoreError) o;
        return Objects.equals(field, coreError.field) &&
                Objects.equals(message, coreError.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, message);
    }

    @Override
    public String toString() {
        return "CoreError{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
