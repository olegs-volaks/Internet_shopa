package eu.retarded.internetstore.core.requests.user;

public class DeleteUserRequest {

    private final long userIdToDelete;

    public DeleteUserRequest(long userIdToDelete) {
        this.userIdToDelete = userIdToDelete;
    }

    public long getUserIdToDelete() {
        return userIdToDelete;
    }
}

