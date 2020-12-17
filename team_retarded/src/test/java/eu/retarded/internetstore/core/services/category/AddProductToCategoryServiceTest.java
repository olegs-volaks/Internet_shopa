package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.AddProductToCategoryResponse;
import eu.retarded.internetstore.core.services.validators.category.AddProductToCategoryValidator;
import eu.retarded.internetstore.database.ProductDatabase;
import eu.retarded.internetstore.database.categories.category.ProductListCategory;
import eu.retarded.internetstore.database.categories.database.CategoriesDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AddProductToCategoryServiceTest {
    @Mock
    private CategoriesDatabase categoriesDatabase;
    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private ProductListCategory productListCategory;
    @Mock
    private AddProductToCategoryValidator validator;
    @InjectMocks
    private AddProductToCategoryService subject;

    @Test
    void should_return_response_with_errors_when_validation_fails() {
        AddProductToCategoryRequest request = new AddProductToCategoryRequest(-1, -1);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ProductID", "Must not be empty or negative"));
        errors.add(new CoreError("CategoryID", "Must not be empty or negative"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddProductToCategoryResponse response = subject.execute(request);
        assertThat(response.hasErrors()).isTrue();
        assertThat(response.getErrors().size()).isEqualTo(2);
        assertThat(response.getErrors()).allMatch(coreError -> coreError.getField().equals("ProductID") ||
                coreError.getMessage().equals("Must not be empty or negative"));
        Mockito.verifyNoInteractions(categoriesDatabase);
        Mockito.verifyNoInteractions(productDatabase);
    }

    @Test
    void should_return_response_with_errors_when_validation_fails_notInDataBase() {
        AddProductToCategoryRequest request = new AddProductToCategoryRequest(1, 1);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ProductID", "Product with this ID does not exist"));
        errors.add(new CoreError("CategoryID", "Product with this ID does not exist"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddProductToCategoryResponse response = subject.execute(request);
        assertThat(response.hasErrors()).isTrue();
        assertThat(response.getErrors().size()).isEqualTo(2);
        assertThat(response.getErrors()).allMatch(coreError -> coreError.getField().equals("ProductID") ||
                coreError.getMessage().equals("Product with this ID does not exist"));
        Mockito.verifyNoInteractions(categoriesDatabase);
        Mockito.verifyNoInteractions(productDatabase);
    }

    @Test
    void should_add_product_to_category() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddProductToCategoryResponse response = subject.execute(new AddProductToCategoryRequest(1, 1));
        assertThat(response.hasErrors()).isFalse();
    }

}