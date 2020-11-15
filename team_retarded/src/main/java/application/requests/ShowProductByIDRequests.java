package application.requests;

public class ShowProductByIDRequests {

    private final Long getById;

    public ShowProductByIDRequests(Long getById) {
        this.getById = getById;
    }

    public Long getById() {
        return getById;
    }
}
