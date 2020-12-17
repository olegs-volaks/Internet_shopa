package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.category.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.AddProductToCategoryResponse;
import eu.retarded.internetstore.core.services.validators.category.AddProductToCategoryValidator;
import eu.retarded.internetstore.database.ProductDatabase;
import eu.retarded.internetstore.database.categories.category.ProductListCategory;
import eu.retarded.internetstore.database.categories.database.CategoriesDatabase;
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
        Optional<ProductListCategory> categoryID = categoriesDatabase.getCategory(request.AddProductToCategoryCategoryID());
        Optional<Product> productID = productDatabase.getById(request.AddProductToCategoryProductID());

        if (categoryID.isPresent() && productID.isPresent()) {
            categoryID.get().add(productID.get());
        }
        return new AddProductToCategoryResponse(request.AddProductToCategoryCategoryID(), request.AddProductToCategoryProductID());
    }
}
