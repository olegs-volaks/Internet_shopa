package application;

import application.core.services.category.AddCategoryService;
import application.core.services.category.AddProductToCategoryService;
import application.core.services.category.DeleteCategoryService;
import application.core.services.category.DeleteProductFromCategoryService;
import application.core.services.product.*;
import application.core.services.validators.category.AddCategoryValidator;
import application.core.services.validators.category.AddProductToCategoryValidator;
import application.core.services.validators.category.DeleteCategoryValidator;
import application.core.services.validators.category.DeleteProductFromCategoryValidator;
import application.core.services.validators.product.AddProductValidator;
import application.core.services.validators.product.DeleteProductValidator;
import application.core.services.validators.product.GetProductByIdValidator;
import application.core.services.validators.product.SearchProductValidator;
import application.database.ListProductDatabase;
import application.database.ProductDatabase;
import application.database.categories.database.CategoriesDatabase;
import application.database.categories.database.ListCategoriesDatabase;
import application.ui.category.AddCategoryUIAction;
import application.ui.category.AddProductToCategoryUIAction;
import application.ui.category.DeleteCategoryUIAction;
import application.ui.category.DeleteProductFromCategoryUIAction;
import application.ui.product.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(CategoriesDatabase.class, new ListCategoriesDatabase());
        beans.put(ProductDatabase.class, new ListProductDatabase(getBean(CategoriesDatabase.class)));

        beans.put(ExitUIAction.class, new ExitUIAction());

        beans.put(ShowAllProductsService.class, new ShowAllProductsService(getBean(ProductDatabase.class)));
        beans.put(ShowAllProductsUIAction.class, new ShowAllProductsUIAction(getBean(ShowAllProductsService.class)));

        beans.put(ClearAllProductsService.class, new ClearAllProductsService(getBean(ProductDatabase.class)));
        beans.put(ClearAllProductsUIAction.class, new ClearAllProductsUIAction(getBean(ClearAllProductsUIAction.class)));

        beans.put(AddProductValidator.class, new AddProductValidator());
        beans.put(AddProductService.class, new AddProductService(getBean(ProductDatabase.class),
                getBean(AddProductValidator.class)));
        beans.put(AddProductUIAction.class, new AddProductUIAction(getBean(AddProductService.class)));

        beans.put(GetProductByIdValidator.class, new GetProductByIdValidator());
        beans.put(GetProductByIdService.class, new GetProductByIdService(getBean(ProductDatabase.class),
                getBean(GetProductByIdValidator.class)));
        beans.put(GetProductByIdUIAction.class, new GetProductByIdUIAction(getBean(GetProductByIdService.class)));

        beans.put(DeleteProductValidator.class, new DeleteProductValidator());
        beans.put(DeleteProductService.class, new DeleteProductService(getBean(ProductDatabase.class),
                getBean(DeleteProductValidator.class)));
        beans.put(DeleteProductUIAction.class, new DeleteProductUIAction(getBean(DeleteProductService.class)));

        beans.put(SearchProductValidator.class, new SearchProductValidator());
        beans.put(SearchProductService.class, new SearchProductService(getBean(ProductDatabase.class),
                getBean(SearchProductValidator.class)));
        beans.put(SearchProductUIAction.class, new SearchProductUIAction(getBean(SearchProductService.class)));

        beans.put(AddCategoryValidator.class, new AddCategoryValidator());
        beans.put(AddCategoryService.class, new AddCategoryService(getBean(CategoriesDatabase.class),
                getBean(AddCategoryValidator.class)));
        beans.put(AddCategoryUIAction.class, new AddCategoryUIAction(getBean(AddCategoryService.class)));

        beans.put(DeleteCategoryValidator.class, new DeleteCategoryValidator());
        beans.put(DeleteCategoryService.class, new DeleteCategoryService(getBean(CategoriesDatabase.class),
                getBean(DeleteCategoryValidator.class)));
        beans.put(DeleteCategoryUIAction.class, new DeleteCategoryUIAction(getBean(DeleteCategoryService.class)));

        beans.put(AddProductToCategoryValidator.class, new AddProductToCategoryValidator());
        beans.put(AddProductToCategoryService.class,
                new AddProductToCategoryService(getBean(CategoriesDatabase.class), getBean(ProductDatabase.class),
                        getBean(AddProductToCategoryValidator.class)));
        beans.put(AddProductToCategoryUIAction.class, new AddProductToCategoryUIAction(getBean(AddProductToCategoryService.class)));

        beans.put(DeleteProductFromCategoryValidator.class, new DeleteProductFromCategoryValidator());
        beans.put(DeleteProductFromCategoryService.class,
                new DeleteProductFromCategoryService(getBean(CategoriesDatabase.class), getBean(ProductDatabase.class),
                        getBean(DeleteProductFromCategoryValidator.class)));
        beans.put(DeleteProductFromCategoryUIAction.class, new DeleteProductFromCategoryUIAction(getBean(DeleteProductFromCategoryService.class)));

        /*
        * Template *
        *
        beans.put(TempValidator.class, new TempValidator());
        beans.put(TempService.class, new TempService(getBean(TempDatabase.class),
                getBean(TempValidator.class)));
        beans.put(TempUIAction.class, new TempUIAction(getBean(TempService.class)));
        */
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
