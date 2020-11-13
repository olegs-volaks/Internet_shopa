package application.ui;


import application.services.ShowAllProductService;


public class ShowAllProductUIAction implements UIAction {

    private final ShowAllProductService service;

    public ShowAllProductUIAction(ShowAllProductService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        System.out.println("All products are successfully found:  ");
        for (int i = 0; i < service.showAllProducts().size(); i++) {
            System.out.println(service.showAllProducts().get(i));
        }
    }
}
