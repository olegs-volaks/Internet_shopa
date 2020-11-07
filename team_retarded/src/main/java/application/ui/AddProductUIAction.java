package application.ui;

import application.services.AddProductService;

public class AddProductUIAction implements UIAction {

    private final AddProductService service;

    public AddProductUIAction(AddProductService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
