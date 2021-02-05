package eu.retarded.internetstore.core.requests.product;

public class SearchProductRequest {

    private final String keyWord;
    private Ordering ordering;
    private Paging paging;

    public SearchProductRequest(String keyWord) {
        this.keyWord =keyWord;

    }

    public SearchProductRequest(String keyWord, Ordering ordering, Paging paging) {
        this.keyWord = keyWord;
        this.ordering = ordering;
        this.paging = paging;
    }

    public SearchProductRequest(String keyWord, Paging paging) {
        this.keyWord = keyWord;
        this.paging = paging;
    }

    public SearchProductRequest(String keyWord, Ordering ordering) {
        this.keyWord = keyWord;
        this.ordering = ordering;
    }

    public String getKeyWord() {
        return keyWord;
    }


    public Ordering getOrdering() {
        return ordering;
    }

    public Paging getPaging() {
        return paging;
    }

    public boolean isNameProvided() {
        return this.keyWord != null && !this.keyWord.isEmpty();
    }
}
