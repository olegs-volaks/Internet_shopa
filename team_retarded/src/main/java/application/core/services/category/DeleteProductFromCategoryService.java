package application.core.services.category;

import application.core.requests.category.DeleteProductFromCategoryRequest;
import application.core.responses.CoreError;
import application.core.responses.category.DeleteProductFromCategoryResponse;
import application.core.services.validators.category.DeleteProductFromCategoryValidator;
import application.database.ProductDatabase;
import application.database.categories.category.ProductListCategory;
import application.database.categories.database.CategoriesDatabase;
import application.items.Product;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.List;
import java.util.Optional;

@DIComponent
public class DeleteProductFromCategoryService {
    @DIDependency
    private  CategoriesDatabase categoriesDatabase;
    @DIDependency
    private  ProductDatabase productDatabase;
    @DIDependency
    private  DeleteProductFromCategoryValidator validator;



    public DeleteProductFromCategoryResponse execute(DeleteProductFromCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductFromCategoryResponse(errors);
        }
        Optional<ProductListCategory> categoryID = categoriesDatabase.getCategory(request.DeleteProductFromCategoryCategoryID());
        Optional <Product> productID = productDatabase.getById(request.DeleteProductFromCategoryProductId());

        if (categoryID .isPresent() && productID.isPresent()){
            categoryID.get().remove(productID.get());
        }
        return new DeleteProductFromCategoryResponse(request.DeleteProductFromCategoryCategoryID(),
                request.DeleteProductFromCategoryProductId());
    }
}
