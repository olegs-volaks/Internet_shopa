package eu.retarded.internetstore.core.requests.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddUserRequest {

    private final String login;
    private final String password;
    private final int role;
    private final String name;
    private final String surname;
    private final String email;
}
