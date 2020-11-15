package application.ui;


import application.core.requests.ShowAllProductsRequest;
import application.core.responses.ShowAllProductsResponse;
import application.core.services.ShowAllProductsService;


public class ShowAllProductsUIAction implements UIAction {

    private final ShowAllProductsService service;

    public ShowAllProductsUIAction(ShowAllProductsService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        ShowAllProductsResponse response = service.execute(new ShowAllProductsRequest());
        if (!response.hasErrors()) {
            System.out.println("All products:");
            response.getProducts().forEach(System.out::println);
        }
    }
}
