package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.UpdateProductRequest;
import eu.retarded.internetstore.core.responses.product.UpdateProductResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class UpdateProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public UpdateProductResponse execute(UpdateProductRequest request) {
        Set<ConstraintViolation<UpdateProductRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateProductResponse(errors);
        }

        Product resultProduct = productRepository.getOne(request.getId());
        resultProduct.setId(request.getId());
        resultProduct.setName(request.getName());
        resultProduct.setDescription(request.getDescription());
        resultProduct.setPrice(BigDecimal.valueOf(request.getPrice()));
        resultProduct.setCount(request.getCount());
        return new UpdateProductResponse(resultProduct);
    }
}
