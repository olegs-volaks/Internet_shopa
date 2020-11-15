package application.services;
import application.bd.Database;
import application.items.Product;
import application.requests.ShowAllProductRequests;
import application.requests.ShowProductByIDRequests;
import application.responses.ShowProductByIDResponse;
import java.util.Optional;

public class ShowProductByIDService {

    private final Database db;
    private long id;

    public ShowProductByIDService(Database db) {
        this.db = db;
    }

//    public Optional<Product> getById(Long id) {
//        return db.getById(id);
//    }

    public ShowProductByIDResponse execute(ShowProductByIDRequests requests) {
        Optional<Product> products;
        return new ShowProductByIDResponse(products);
    }
}
