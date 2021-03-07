package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.product.AddProductToCategoryResponse;
import eu.retarded.internetstore.database.CategoryRepository;
import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class AddProductToCategoryService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Validator validator;

    @Transactional
    public AddProductToCategoryResponse execute(AddProductToCategoryRequest request) {
        Set<ConstraintViolation<AddProductToCategoryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductToCategoryResponse(errors);
        }
        Product resultProduct = productRepository.getOne(request.getProductId());
        resultProduct.setCategory(categoryRepository.getOne(request.getCategoryId()));
        productRepository.save(resultProduct);
        return new AddProductToCategoryResponse(productRepository.getOne(request.getProductId()).equals(resultProduct));
    }
}
