package eu.retarded.internetstore.console_ui.category;

import eu.retarded.internetstore.console_ui.UIAction;
import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllCategoryUIAction implements UIAction {

    @Autowired
    private CategoriesDatabase database;

    @Override
    public void execute() {
        List<Category> categories = database.getCategoryList();
        categories.forEach(System.out::println);
    }
}
