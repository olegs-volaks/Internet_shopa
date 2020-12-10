package application.core.services.validators.category;

import application.core.requests.category.AddProductToCategoryRequest;
import application.database.ProductDatabase;
import application.database.categories.database.CategoriesDatabase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class AddProductToCategoryValidatorTest {


    @Mock
    private CategoriesDatabase categoriesDatabase;
    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private AddProductToCategoryRequest request;
    @InjectMocks
    private AddProductToCategoryValidator subject;



    @Test
    void CategoryID_is_negative() {
    }
    @Test
    void should_by_successful() {

    }


}