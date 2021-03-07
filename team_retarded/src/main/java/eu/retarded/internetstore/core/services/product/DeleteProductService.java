package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.product.DeleteProductResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class DeleteProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Validator validator;

    @Transactional
    public DeleteProductResponse execute(DeleteProductRequest request) {
        Set<ConstraintViolation<DeleteProductRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductResponse(errors);
        }
        productRepository.deleteById(request.getProductId());
        return new DeleteProductResponse(!productRepository.existsById(request.getProductId()));
    }
}

