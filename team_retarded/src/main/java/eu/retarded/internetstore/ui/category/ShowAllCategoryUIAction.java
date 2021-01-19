package eu.retarded.internetstore.ui.category;

import eu.retarded.internetstore.core.domain.ProductCategory;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllCategoryUIAction implements UIAction {

    @Autowired
    private CategoriesDatabase database;

    @Override
    public void execute() {
        List<ProductCategory> categories = database.getCategoryList();
        categories.forEach(System.out::println);
    }
}
