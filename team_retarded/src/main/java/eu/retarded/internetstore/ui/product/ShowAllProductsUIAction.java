package eu.retarded.internetstore.ui.product;


import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.product.ShowAllProductsResponse;
import eu.retarded.internetstore.core.services.product.ShowAllProductsService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
