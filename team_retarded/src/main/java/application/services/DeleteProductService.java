package application.services;
import application.bd.Database;
import application.requests.DeleteProductRequests;
import application.responses.CoreError;
import application.responses.DeleteProductResponse;
import java.util.ArrayList;
import java.util.List;

public class DeleteProductService {

    private final Database db;

    public DeleteProductService(Database db) {
        this.db = db;
    }

    public void deleteProduct(Long id){
        db.delete(id);
    }

    public DeleteProductResponse execute(DeleteProductRequests requests) {
        if (requests.getProductId() == null) {
            CoreError error = new CoreError("id","Please insert id product,it can not be empty!");
            List<CoreError> errors = new ArrayList<>();
            errors.add(error);
          //  return new DeleteProductResponse(errors);
        }
        boolean isProductDeleted = db.delete(requests.getProductId());
        return new DeleteProductResponse(isProductDeleted);
    }
}
