package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.category.DeleteProductFromCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.DeleteProductFromCategoryResponse;
import eu.retarded.internetstore.core.services.validators.category.DeleteProductFromCategoryValidator;
import eu.retarded.internetstore.database.ProductDatabase;
import eu.retarded.internetstore.database.categories.category.ProductListCategory;
import eu.retarded.internetstore.database.categories.database.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DeleteProductFromCategoryService {
    @Autowired
    private CategoriesDatabase categoriesDatabase;
    @Autowired
    private ProductDatabase productDatabase;
    @Autowired
    private DeleteProductFromCategoryValidator validator;


    public DeleteProductFromCategoryResponse execute(DeleteProductFromCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductFromCategoryResponse(errors);
        }
        Optional<ProductListCategory> categoryID = categoriesDatabase.getCategory(request.DeleteProductFromCategoryCategoryID());
        Optional<Product> productID = productDatabase.getById(request.DeleteProductFromCategoryProductId());

        if (categoryID.isPresent() && productID.isPresent()) {
            categoryID.get().remove(productID.get());
        }
        return new DeleteProductFromCategoryResponse(request.DeleteProductFromCategoryCategoryID(),
                request.DeleteProductFromCategoryProductId());
    }
}
