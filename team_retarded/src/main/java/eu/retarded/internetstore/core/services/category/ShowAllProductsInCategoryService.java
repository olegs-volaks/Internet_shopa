package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.category.ShowAllProductsInCategoryRequest;
import eu.retarded.internetstore.core.responses.category.ShowAllProductsInCategoryResponse;
import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ShowAllProductsInCategoryService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Validator validator;

    @Transactional
    public ShowAllProductsInCategoryResponse execute(ShowAllProductsInCategoryRequest request) {
        Set<ConstraintViolation<ShowAllProductsInCategoryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShowAllProductsInCategoryResponse(errors);
        }
        List<Product> products;
        if (request.getPageable() == null) {
            products = productRepository.findAllByCategory_Id(request.getCategoryId());
            return new ShowAllProductsInCategoryResponse(null, products);
        }
        Page<Product> productPage = productRepository.findAllByCategory_Id(request.getCategoryId(),
                request.getPageable());
        return new ShowAllProductsInCategoryResponse(productPage, productPage.toList());
    }
}