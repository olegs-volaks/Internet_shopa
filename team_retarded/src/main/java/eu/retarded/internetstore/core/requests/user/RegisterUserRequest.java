package eu.retarded.internetstore.core.requests.user;

import eu.retarded.internetstore.core.services.validators.PasswordsMatch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
@PasswordsMatch(baseField = "password", matchField = "passwordConfirm")
public class RegisterUserRequest {

    @NotBlank(message = "Login must not be empty!")
    @Length(min = 6, max = 32, message = "Username must be between 6 and 32 characters")
    private final String userName;

    @NotBlank(message = "Password must not be empty!")
    @Length(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
    private final String password;

    @NotBlank(message = "Password must not be empty!")
    @Length(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
    private final String passwordConfirm;

    @NotBlank(message = "Name must not be empty!")
    @Length(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private final String name;

    @NotBlank(message = "Surname must not be empty!")
    @Length(min = 2, max = 100, message = "Surname must be between 2 and 100 characters")
    private final String surname;

    @NotBlank(message = "Email must not be empty!")
    @Email(message = "Incorrect email format!")
    private final String email;
}
