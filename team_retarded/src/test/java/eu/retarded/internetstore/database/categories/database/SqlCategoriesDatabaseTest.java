package eu.retarded.internetstore.database.categories.database;

import eu.retarded.internetstore.core.domain.ProductCategory;
import eu.retarded.internetstore.database.category.SqlCategoriesDatabase;
import org.junit.jupiter.api.Test;

class SqlCategoriesDatabaseTest {

    private SqlCategoriesDatabase subject = new SqlCategoriesDatabase();

    @Test
    void add_category_test() {
        subject.addCategory(new ProductCategory("gdffdggd"));
    }
}