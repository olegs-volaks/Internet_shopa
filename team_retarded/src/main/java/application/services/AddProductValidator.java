package application.services;
import application.requests.AddProductRequests;
import application.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

public class AddProductValidator {

    public List<CoreError> validate(AddProductRequests requests) {
        List<CoreError> errors = new ArrayList<>();

        String name = requests.getName();
        if (name == null || name.isEmpty()) {
            errors.add(new CoreError("name","Please,write a name of product,it can not be empty! "));
        }
        String description = requests.getDescription();
        if (description == null || description.isEmpty()) {
            errors.add(new CoreError("description","Please,write a description of product,it can not be empty! "));
        }
        double price = requests.getPrice();
        if (price == 0){
            errors.add(new CoreError("price","Please,write a price of product,it can not be empty!"));
        }
        return errors;
    }
}
