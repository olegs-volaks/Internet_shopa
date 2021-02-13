package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.UpdateProductRequest;
import eu.retarded.internetstore.core.responses.product.UpdateProductResponse;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class UpdateProductService {
    @Autowired
    private ProductDatabase productDatabase;

    @Autowired
    private Validator validator;

    @Transactional
    public UpdateProductResponse execute(UpdateProductRequest request) {
        Set<ConstraintViolation<UpdateProductRequest>> errors = validator.validate(request);
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
