package eu.retarded.internetstore.core.requests.product;

public class SearchProductRequest {

    private final String keyWord;
    private  String sorting;
    //private Ordering ordering;
    //private Paging paging;

    public SearchProductRequest(String keyWord, String sorting) {
        this.keyWord =keyWord;
        this.sorting =sorting;
    }

    public SearchProductRequest(String keyWord) {
        this.keyWord =keyWord;
    }




    public String getKeyWord() {
        return keyWord;
    }
    public String getSorting() { return sorting;
    }



    public boolean isNameProvided() {
        return this.keyWord != null && !this.keyWord.isEmpty();
    }
}
