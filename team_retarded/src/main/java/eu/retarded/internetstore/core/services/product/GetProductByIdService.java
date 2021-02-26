package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.responses.product.GetProductByIdResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class GetProductByIdService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Validator validator;

    @Transactional
    public GetProductByIdResponse execute(GetProductByIdRequest request) {
        Set<ConstraintViolation<GetProductByIdRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetProductByIdResponse(errors);
        }
        return new GetProductByIdResponse(productRepository.findById(request.getProductId()).get());
    }
}
