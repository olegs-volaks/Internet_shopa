package eu.retarded.internetstore.core.requests.cart;

public class GetByIdCartRequest {

    private final Long id;

    public GetByIdCartRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
