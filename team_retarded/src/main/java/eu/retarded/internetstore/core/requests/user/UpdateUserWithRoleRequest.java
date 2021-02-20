package eu.retarded.internetstore.core.requests.user;

import eu.retarded.internetstore.core.domain.Role;
import eu.retarded.internetstore.core.services.validators.CartExist;
import eu.retarded.internetstore.core.services.validators.UserExist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class UpdateUserWithRoleRequest {

    @UserExist
    private final long id;

    @NotBlank(message = "Login must not be empty!")
    @Length(min = 6, max = 32, message = "Login must be between 6 and 32 characters")
    private final String login;

    @NotBlank(message = "Password must not be empty!")
    @Length(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
    private final String password;

    @NotBlank(message = "Role must not be empty!")
    @Pattern(regexp = "<1>|<2>|<3>", message = "Must be between 1 and 3 characters")
    private final int role;

    @NotBlank(message = "Name must not be empty!")
    @Length(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private final String name;

    @NotBlank(message = "Surname must not be empty!")
    @Length(min = 2, max = 100, message = "Surname must be between 2 and 100 characters")
    private final String surname;

    @NotBlank(message = "Email must not be empty!")
    @Email(message = "Incorrect email format!")
    private final String email;

    @NotBlank(message = "Status must not be empty!")
    @Pattern(regexp = "<0>|<1>|<2>", message = "Must be between 0 and 2 characters")
    private int status;

    @CartExist
    private final long cartId;

    @NotBlank(message = "Roles must not be empty!")
    private final Set<Role> roles;
}
