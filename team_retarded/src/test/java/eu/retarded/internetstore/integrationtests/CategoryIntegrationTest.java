package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class CategoryIntegrationTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private CategoriesDatabase categoriesDatabase;

    @BeforeEach
    void setUp() {
        categoriesDatabase.clear();
    }


}
