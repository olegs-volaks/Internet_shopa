package eu.retarded.internetstore.core.requests.user;

import eu.retarded.internetstore.core.services.validators.PasswordsMatch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
@PasswordsMatch(baseField = "newPassword", matchField = "newPasswordAgain")
public class ChangeUserPasswordRequest {

    private final long userId;

    @NotBlank(message = "new password must not be empty!")
    @Length(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
    private final String newPassword;

    @NotBlank(message = "new password must not be empty!")
    @Length(min = 6, max = 32, message = "Password must be between 6 and 32 characters")
    private final String newPasswordAgain;

}
