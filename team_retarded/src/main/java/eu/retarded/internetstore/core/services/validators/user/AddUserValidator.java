package eu.retarded.internetstore.core.services.validators.user;

import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddUserValidator {

    public List<CoreError> validate(AddUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateLogin(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        validateRole(request).ifPresent(errors::add);
        validateName(request).ifPresent(errors::add);
        validateSurname(request).ifPresent(errors::add);
        validateEmail(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateLogin(AddUserRequest request) {
        if (request.getLogin() == null || request.getLogin().isEmpty()) {
            return Optional.of(new CoreError("Login", "Must not be empty!"));
        }

        if (request.getLogin().length() < 4 || request.getLogin().length() > 32) {
            return Optional.of(new CoreError("Login", "Must be between 4 and 32 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validatePassword(AddUserRequest request) {
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return Optional.of(new CoreError("Password", "Must not be empty!"));
        }

        if (request.getPassword().length() < 6 || request.getPassword().length() > 32) {
            return Optional.of(new CoreError("Password", "Must be between 6 and 32 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateRole(AddUserRequest request) {
        if (request.getRole() < 0 && request.getRole() > 4) {
            return Optional.of(new CoreError("Role", "Must be between 1 and 3 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateName(AddUserRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return Optional.of(new CoreError("Name", "Must not be empty!"));
        }

        if (request.getName().length() < 3 || request.getName().length() > 32) {
            return Optional.of(new CoreError("Name", "Must be between 3 and 32 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateSurname(AddUserRequest request) {
        if (request.getSurname() == null || request.getSurname().isEmpty()) {
            return Optional.of(new CoreError("Surname", "Must not be empty!"));
        }

        if (request.getSurname().length() < 3 || request.getSurname().length() > 32) {
            return Optional.of(new CoreError("Surname", "Must be between 3 and 32 characters"));
        }
        return Optional.empty();
    }

    private Optional<CoreError> validateEmail(AddUserRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return Optional.of(new CoreError("Email", "Must not be empty!"));
        }

        if (request.getEmail().length() < 6 || request.getEmail().length() > 32) {
            return Optional.of(new CoreError("Email", "Must be between 8 and 32 characters"));
        }

        if (!request.getEmail().contains("@")) {
            return Optional.of(new CoreError("Email", "Must contain \"@\""));
        }
        return Optional.empty();
    }
}