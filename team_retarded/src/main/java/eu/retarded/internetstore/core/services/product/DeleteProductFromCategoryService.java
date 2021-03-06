package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.DeleteProductFromCategoryRequest;
import eu.retarded.internetstore.core.responses.product.DeleteProductFromCategoryResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class DeleteProductFromCategoryService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public DeleteProductFromCategoryResponse execute(DeleteProductFromCategoryRequest request) {
        Set<ConstraintViolation<DeleteProductFromCategoryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductFromCategoryResponse(errors);
        }

        Product resultProduct = productRepository.getOne(request.getProductId());
        resultProduct.setCategory(null);
        productRepository.save(resultProduct);

        return new DeleteProductFromCategoryResponse(productRepository.getOne(request.getProductId()).equals(resultProduct));
    }
}
