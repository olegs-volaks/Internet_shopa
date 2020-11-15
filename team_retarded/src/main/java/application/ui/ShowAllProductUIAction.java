package application.ui;


import application.requests.ShowAllProductRequests;
import application.responses.ShowAllProductResponse;
import application.services.ShowAllProductService;


public class ShowAllProductUIAction implements UIAction {

    private final ShowAllProductService service;

    public ShowAllProductUIAction(ShowAllProductService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        System.out.println("Product list:  ");
        for (int i = 0; i < service.showAllProducts().size(); i++) {
            System.out.println(service.showAllProducts().get(i));
        }
        ShowAllProductRequests requests = new ShowAllProductRequests();
        ShowAllProductResponse response = service.execute(requests);
        response.getShowAllProducts().forEach(System.out::println);
        System.out.println("All products are successfully found:");
    }
}
