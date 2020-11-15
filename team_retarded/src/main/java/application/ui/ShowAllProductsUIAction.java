package application.ui;


import application.core.services.ShowAllProductsService;


public class ShowAllProductsUIAction implements UIAction {

    private final ShowAllProductsService service;

    public ShowAllProductsUIAction(ShowAllProductsService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        System.out.println("All products are successfully found:  ");
        for (int i = 0; i < service.execute().size(); i++) {
            System.out.println(service.execute().get(i));
        }
    }
}
