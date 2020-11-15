package application.services;
import application.bd.Database;
import application.items.Product;
import application.requests.ShowAllProductRequests;
import application.requests.ShowProductByIDRequests;
import application.responses.ShowProductByIDResponse;
import java.util.Optional;

public class ShowProductByIDService {

    private final Database db;

    public ShowProductByIDService(Database db) {
        this.db = db;
    }

    public ShowProductByIDResponse execute(ShowProductByIDRequests requests) {
        Optional<Product> products = db.getById(requests.getById());
        return new ShowProductByIDResponse(products);
    }
}
