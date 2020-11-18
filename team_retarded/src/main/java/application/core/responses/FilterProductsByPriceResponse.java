package application.core.responses;

import application.items.Product;

import java.util.List;

public class FilterProductsByPriceResponse extends CoreResponse{

    private  List <Product> productsByFilter;

    //public FilterProductsByPriceResponse() {
        //this.productsByFilter = productsByFilter;
    //}

    public FilterProductsByPriceResponse(List<CoreError> errors,List <Product> productsByFilter) {
        super(errors);
    }



    public List <Product> getProductsByFilter() {
        return productsByFilter;
    }
}
