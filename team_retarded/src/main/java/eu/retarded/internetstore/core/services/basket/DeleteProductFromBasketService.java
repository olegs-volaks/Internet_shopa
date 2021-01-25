package eu.retarded.internetstore.core.services.basket;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.basket.DeleteProductFromBasketRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.basket.DeleteProductFromBasketResponse;
import eu.retarded.internetstore.core.services.validators.basket.DeleteProductFromBasketValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class DeleteProductFromBasketService {

    @Autowired
    private UsersDatabase usersDatabase;
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private DeleteProductFromBasketValidator validator;

    public DeleteProductFromBasketResponse execute(DeleteProductFromBasketRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductFromBasketResponse(errors);
        }
        Optional<User> user = usersDatabase.getUserById(request.getUserId());
        Optional<Product> product = productDatabase.getById(request.getProductId());

        if (user.isPresent() && product.isPresent()) {
            //user.get().getUsersBasket().removeProduct(product.get());
        }

        return new DeleteProductFromBasketResponse(user.isPresent() && product.isPresent());
    }

}
