package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.ProductCategory;
import eu.retarded.internetstore.core.requests.product.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.AddProductToCategoryResponse;
import eu.retarded.internetstore.core.services.validators.product.AddProductToCategoryValidator;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddProductToCategoryService {
    @Autowired
    private CategoriesDatabase categoriesDatabase;
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private AddProductToCategoryValidator validator;


    public AddProductToCategoryResponse execute(AddProductToCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductToCategoryResponse(errors);
        }
        Optional<ProductCategory> categoryID = categoriesDatabase.getCategory(request.AddProductToCategoryCategoryID());
        Optional<Product> productID = productDatabase.getById(request.AddProductToCategoryProductID());


        return new AddProductToCategoryResponse
                    (productDatabase.addProductToCategory(productID.get().getId(), categoryID.get().getId()));


    }
}
