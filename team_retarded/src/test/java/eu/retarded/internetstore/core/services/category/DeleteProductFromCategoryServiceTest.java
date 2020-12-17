package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.DeleteProductFromCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.DeleteProductFromCategoryResponse;
import eu.retarded.internetstore.core.services.validators.category.DeleteProductFromCategoryValidator;
import eu.retarded.internetstore.database.ProductDatabase;
import eu.retarded.internetstore.database.categories.database.CategoriesDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class DeleteProductFromCategoryServiceTest {
    @Mock
    private CategoriesDatabase categoriesDatabase;
    @Mock
    private ProductDatabase productDatabase;
    @Mock
    private DeleteProductFromCategoryValidator validator;
    @InjectMocks
    private DeleteProductFromCategoryService subject;

    @Test
    void should_return_response_with_errors_when_validation_fails() {
        DeleteProductFromCategoryRequest request = new DeleteProductFromCategoryRequest(-1, -1);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ProductID", "Must not be empty or negative"));
        errors.add(new CoreError("CategoryID", "Must not be empty or negative"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        DeleteProductFromCategoryResponse response = subject.execute(request);
        Assertions.assertThat(response.hasErrors()).isTrue();
        Assertions.assertThat(response.getErrors().size()).isEqualTo(2);
        Assertions.assertThat(response.getErrors()).allMatch(coreError -> coreError.getField().equals("ProductID") ||
                coreError.getMessage().equals("Must not be empty or negative"));
        Mockito.verifyNoInteractions(categoriesDatabase);
        Mockito.verifyNoInteractions(productDatabase);
    }

    @Test
    void should_return_response_with_errors_when_validation_fails_notInDataBase() {
        DeleteProductFromCategoryRequest request = new DeleteProductFromCategoryRequest(1, 1);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ProductID", "Product with this ID does not exist"));
        errors.add(new CoreError("CategoryID", "Product with this ID does not exist"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        DeleteProductFromCategoryResponse response = subject.execute(request);
        Assertions.assertThat(response.hasErrors()).isTrue();
        Assertions.assertThat(response.getErrors().size()).isEqualTo(2);
        Assertions.assertThat(response.getErrors()).allMatch(coreError -> coreError.getField().equals("ProductID") ||
                coreError.getMessage().equals("Product with this ID does not exist"));
        Mockito.verifyNoInteractions(categoriesDatabase);
        Mockito.verifyNoInteractions(productDatabase);
    }

    @Test
    void should_delete_product_in_category() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        DeleteProductFromCategoryResponse response = subject.execute(new DeleteProductFromCategoryRequest(1, 1));
        Assertions.assertThat(response.hasErrors()).isFalse();
    }

}