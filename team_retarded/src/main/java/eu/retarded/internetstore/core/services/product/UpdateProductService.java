package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.UpdateProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.UpdateProductResponse;
import eu.retarded.internetstore.core.services.validators.product.UpdateProductValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UpdateProductService {
    @Autowired
    private ProductDatabase productDatabase;

    @Autowired
    private UpdateProductValidator validator;

    @Transactional
    public UpdateProductResponse execute(UpdateProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateProductResponse(errors);
        }
        long id = request.getId();

        Product resultProduct = new Product();

        resultProduct.setId(id);
        resultProduct.setName(request.getName());
        resultProduct.setDescription(request.getDescription());
        resultProduct.setPrice(request.getPrice());

        productDatabase.updateProduct(resultProduct);
        return new UpdateProductResponse(id);
    }
}
