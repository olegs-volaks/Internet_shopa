package eu.retarded.internetstore.core.requests.user;

public class DeleteUserRequest {

    private final long userId;

    public DeleteUserRequest(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }
}

