package application.ui.product;

import application.core.services.product.SearchProductService;
import application.ui.UIAction;

public class SearchProductUIAction implements UIAction {

    private final SearchProductService service;

    public SearchProductUIAction(SearchProductService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        //todo: добавить логику
    }
}
