package eu.retarded.internetstore.core.requests.product;

import lombok.Getter;

@Getter
public class SearchProductRequest {

    private String keyWord;
    private String sorting;
    private int page;

    public SearchProductRequest(String keyWord, String sorting, int page) {
        this.keyWord = keyWord;
        this.sorting = sorting;
        this.page = page;
    }
}
