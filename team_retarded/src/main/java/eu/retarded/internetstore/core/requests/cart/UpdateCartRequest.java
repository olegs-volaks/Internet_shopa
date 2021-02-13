package eu.retarded.internetstore.core.requests.cart;


//@Getter
//@RequiredArgsConstructor
public class UpdateCartRequest {

    private final long id;
    private final long user_id;
    private final int status;

    public UpdateCartRequest(long id, long user_id, int status) {
        this.id = id;
        this.user_id = user_id;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public long getUser_id() {
        return user_id;
    }

    public int getStatus() {
        return status;
    }
}
