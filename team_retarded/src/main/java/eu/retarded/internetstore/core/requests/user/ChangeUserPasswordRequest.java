package eu.retarded.internetstore.core.requests.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangeUserPasswordRequest {

    private final long userId;
    private final String oldPassword;
    private final String newPassword;

}
