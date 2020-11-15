package application.services;
import application.bd.Database;
import application.items.Product;
import application.requests.ShowAllProductRequests;
import application.responses.ShowAllProductResponse;
import java.util.List;

public class ShowAllProductService {

    private final Database db;

    public ShowAllProductService(Database db) {
        this.db = db;
    }

    public List<Product> showAllProducts() {
        return db.getList();
    }

    public ShowAllProductResponse execute(ShowAllProductRequests requests) {
        List<Product> products = db.getList();
        return new ShowAllProductResponse(products);
    }
}
