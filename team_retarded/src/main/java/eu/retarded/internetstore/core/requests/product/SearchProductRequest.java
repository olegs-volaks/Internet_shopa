package eu.retarded.internetstore.core.requests.product;

public class SearchProductRequest {

    private final String keyWord;
    private  String sorting;
    private int page;

    public SearchProductRequest(String keyWord, String sorting, int page) {
        this.keyWord =keyWord;
        this.sorting =sorting;
        this.page=page;
    }


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
    public String getSorting() {
        return sorting;
    }
    public int getPage(){
        return page;
    }



    public boolean isNameProvided() {
        return this.keyWord != null && !this.keyWord.isEmpty();
    }
}
