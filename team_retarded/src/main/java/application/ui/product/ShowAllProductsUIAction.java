package application.ui.product;


import application.core.requests.product.ShowAllProductsRequest;
import application.core.responses.product.ShowAllProductsResponse;
import application.core.services.product.ShowAllProductsService;
import application.ui.UIAction;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class ShowAllProductsUIAction implements UIAction {

    @Autowired
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
