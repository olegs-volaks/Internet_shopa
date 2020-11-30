package application.core.requests.product;

public class Paging {

    private final Integer pageNumber;
    private final Integer pageSize;

    public Paging(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }
}
