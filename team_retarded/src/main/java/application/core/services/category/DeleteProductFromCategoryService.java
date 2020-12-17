package application.core.services.category;

import application.core.requests.category.DeleteProductFromCategoryRequest;
import application.core.responses.CoreError;
import application.core.responses.category.DeleteProductFromCategoryResponse;
import application.core.services.validators.category.DeleteProductFromCategoryValidator;
import application.database.ProductDatabase;
import application.database.categories.category.ProductListCategory;
import application.database.categories.database.CategoriesDatabase;
import application.items.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DeleteProductFromCategoryService {
    @Autowired private  CategoriesDatabase categoriesDatabase;
    @Autowired private  ProductDatabase productDatabase;
    @Autowired private  DeleteProductFromCategoryValidator validator;



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
