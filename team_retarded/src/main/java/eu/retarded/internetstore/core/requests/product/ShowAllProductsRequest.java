package eu.retarded.internetstore.core.requests.product;


public class ShowAllProductsRequest {

    private Ordering ordering;
    private Paging paging;


    public ShowAllProductsRequest(Ordering ordering, Paging paging) {
        this.ordering = ordering;
        this.paging = paging;
    }

    public ShowAllProductsRequest(Paging paging) {
        this.paging = paging;
    }

    public ShowAllProductsRequest(Ordering ordering) {
        this.ordering = ordering;
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }
}
