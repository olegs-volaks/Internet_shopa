package application.ui.product;


import application.core.requests.product.ShowAllProductsRequest;
import application.core.responses.product.ShowAllProductsResponse;
import application.core.services.product.ShowAllProductsService;
import application.ui.UIAction;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;


@DIComponent
public class ShowAllProductsUIAction implements UIAction {

    @DIDependency
    private ShowAllProductsService service;

    @Override
    public void execute() {
        ShowAllProductsResponse response = service.execute(new ShowAllProductsRequest());
        if (!response.hasErrors()) {
            System.out.println("All products:");
            response.getProducts().forEach(System.out::println);
        }
    }
}
