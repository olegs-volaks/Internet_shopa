package application.ui;

import application.core.services.SearchProductService;

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
