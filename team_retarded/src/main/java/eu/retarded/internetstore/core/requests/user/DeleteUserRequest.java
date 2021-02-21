package eu.retarded.internetstore.core.requests.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteUserRequest {

    private final long userId;
}

