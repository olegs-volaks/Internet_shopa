package application.ui.category;

import application.core.services.category.AddCategoryService;
import application.ui.UIAction;

public class AddCategoryUIAction implements UIAction {

    private final AddCategoryService service;

    public AddCategoryUIAction(AddCategoryService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
