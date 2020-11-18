package application.core.services;

import application.bd.Database;
import application.core.services.validators.FilterProductsByPriceValidator;

public class FilterProductsByPriceService {
    private final Database db;
    private final FilterProductsByPriceValidator validator;

    public FilterProductsByPriceService(Database db, FilterProductsByPriceValidator validator) {
        this.db = db;
        this.validator = validator;
    }







}

