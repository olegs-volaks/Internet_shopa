package application.core.services.category;

import application.core.requests.category.DeleteProductFromCategoryRequest;
import application.core.responses.CoreError;
import application.core.responses.category.DeleteProductFromCategoryResponse;
import application.core.services.validators.category.DeleteProductFromCategoryValidator;
import application.database.ProductDatabase;
import application.database.categories.category.ProductListCategory;
import application.database.categories.database.CategoriesDatabase;
import application.items.Product;

import java.util.List;
import java.util.Optional;

public class DeleteProductFromCategoryService {
    private final CategoriesDatabase categoriesDatabase;
    private final ProductDatabase productDatabase;
    private final DeleteProductFromCategoryValidator validator;

    public DeleteProductFromCategoryService(CategoriesDatabase categoriesDatabase,ProductDatabase productDatabase,
                                            DeleteProductFromCategoryValidator validator) {
        this.categoriesDatabase = categoriesDatabase;
        this.productDatabase = productDatabase;
        this.validator = validator;
    }

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
