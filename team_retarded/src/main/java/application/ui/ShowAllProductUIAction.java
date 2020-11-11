package application.ui;

import application.services.ShowAllProductService;
import java.util.List;
public class ShowAllProductUIAction implements UIAction {

    private final ShowAllProductService service;

    public ShowAllProductUIAction(ShowAllProductService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        System.out.println("All products are successfully found:  ");
        System.out.println(service.showAllProduct());




    }
}
