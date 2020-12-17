package application.core.services.category;

import application.core.requests.category.AddProductToCategoryRequest;
import application.core.responses.CoreError;
import application.core.responses.category.AddProductToCategoryResponse;
import application.core.services.validators.category.AddProductToCategoryValidator;
import application.database.ProductDatabase;
import application.database.categories.category.ProductListCategory;
import application.database.categories.database.CategoriesDatabase;
import application.items.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddProductToCategoryService {

    @Autowired private  CategoriesDatabase categoriesDatabase;
    @Autowired private  ProductDatabase productDatabase;
    @Autowired private  AddProductToCategoryValidator validator;



    public AddProductToCategoryResponse execute(AddProductToCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductToCategoryResponse(errors);
        }
        Optional <ProductListCategory> categoryID = categoriesDatabase.getCategory(request.AddProductToCategoryCategoryID());
        Optional <Product> productID = productDatabase.getById(request.AddProductToCategoryProductID());

        if (categoryID .isPresent() && productID.isPresent()){
            categoryID.get().add(productID.get());
        }
        return new AddProductToCategoryResponse(request.AddProductToCategoryCategoryID(), request.AddProductToCategoryProductID());
    }
}
