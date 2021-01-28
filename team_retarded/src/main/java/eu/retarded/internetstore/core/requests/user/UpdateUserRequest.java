package eu.retarded.internetstore.core.requests.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserRequest {
    private final long id;
    private final int role;
    private final String name;
    private final String surname;
    private final String email;
}
