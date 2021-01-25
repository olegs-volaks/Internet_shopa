package eu.retarded.internetstore.core.services.basket;


import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.basket.AddProductToBasketRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.basket.AddProductToBasketResponse;
import eu.retarded.internetstore.core.services.validators.basket.AddProductToBasketValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddProductToBasketService {


    @Autowired
    private UsersDatabase usersDatabase;
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private AddProductToBasketValidator validator;

    public AddProductToBasketResponse execute(AddProductToBasketRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductToBasketResponse(errors);
        }
        Optional<User> user = usersDatabase.getUserById(request.getUserId());
        Optional<Product> product = productDatabase.getById(request.getProductId());

        if (user.isPresent() && product.isPresent()) {
            //user.get().getUsersBasket().add(product.get(), request.getQuantity());
        }

        return new AddProductToBasketResponse(user.isPresent() && product.isPresent());
    }
}