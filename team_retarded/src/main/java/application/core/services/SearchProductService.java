package application.core.services;

import application.core.requests.Ordering;
import application.core.requests.Paging;
import application.core.requests.SearchProductRequest;
import application.core.responses.CoreError;
import application.core.responses.SearchProductResponse;
import application.core.services.validators.SearchProductValidator;
import application.database.Database;
import application.items.Product;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchProductService {

    private final Database db;
    private final SearchProductValidator validator;

    public SearchProductService(Database db, SearchProductValidator validator) {
        this.db = db;
        this.validator = validator;
    }

    public SearchProductResponse execute(SearchProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchProductResponse(errors, null);
        }

        List<Product> products = db.filter(product -> (product.getName().toLowerCase().contains(request.getName().toLowerCase()))||
                (product.getDescription().toLowerCase().contains(request.getDescription().toLowerCase())));
        products = order(products, request.getOrdering());
        products = paging(products, request.getPaging());

        return new SearchProductResponse(errors,products);
    }

    private List<Product> order(List<Product> products, Ordering ordering) {
        if (ordering != null) {
            Comparator<Product> comparator = ordering.getOrderBy().equals("name")
                    ? Comparator.comparing(Product::getName)
                    : Comparator.comparing(Product::getDescription);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return products.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return products;
        }
    }

    private List<Product> paging(List<Product> products, Paging paging) {
        if (paging.getPageNumber()!=null && paging.getPageSize()!=null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return products.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }
}
