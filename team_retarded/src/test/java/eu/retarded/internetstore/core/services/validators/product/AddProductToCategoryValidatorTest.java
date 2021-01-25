package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AddProductToCategoryValidatorTest {

    @Mock
    private CategoriesDatabase categoriesDatabase;
    @Mock
    private ProductDatabase productDatabase;

    @InjectMocks
    private AddProductToCategoryValidator subject;

    @Test
    void CategoryID_and_ProductID_is_not_exist() {
        AddProductToCategoryRequest request = new AddProductToCategoryRequest(1, 1);
        Mockito.when(categoriesDatabase.getCategory(1L)).thenReturn(Optional.empty());
        Mockito.when(productDatabase.getById(1L)).thenReturn(Optional.empty());
        List<CoreError> actual = subject.validate(request);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("ProductID", "Product with this ID does not exist"));
        expected.add(new CoreError("CategoryID", "Category with this ID does not exist"));
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void CategoryID_is_not_exist() {
        AddProductToCategoryRequest request = new AddProductToCategoryRequest(1, 1);
        Mockito.when(categoriesDatabase.getCategory(1L)).thenReturn(Optional.empty());
        Mockito.when(productDatabase.getById(1L)).
                thenReturn(Optional.of(new Product("Audi", "red1234567890", 345)));
        List<CoreError> actual = subject.validate(request);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("CategoryID", "Category with this ID does not exist"));
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void ProductID_is_not_exist() {
        AddProductToCategoryRequest request = new AddProductToCategoryRequest(1, 1);
        Mockito.when(categoriesDatabase.getCategory(1L)).thenReturn(Optional.of(new Category("Phone")));
        Mockito.when(productDatabase.getById(1L)).thenReturn(Optional.empty());
        List<CoreError> actual = subject.validate(request);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("ProductID", "Product with this ID does not exist"));
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void ProductID_is_negative() {
        AddProductToCategoryRequest request = new AddProductToCategoryRequest(1, -1);
        Mockito.when(categoriesDatabase.getCategory(1L)).thenReturn(Optional.of(new Category("Phone")));
        List<CoreError> actual = subject.validate(request);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("ProductID", "Must not be empty or negative"));
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void CategoryID_is_negative() {
        AddProductToCategoryRequest request = new AddProductToCategoryRequest(-1, 1);
        Mockito.when(productDatabase.getById(1L)).
                thenReturn(Optional.of(new Product("Audi", "red1234567890", 345)));
        List<CoreError> actual = subject.validate(request);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("CategoryID", "Must not be empty or negative"));
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void CategoryID_ProductID_and_is_negative() {
        AddProductToCategoryRequest request = new AddProductToCategoryRequest(-1, -1);
        List<CoreError> actual = subject.validate(request);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("ProductID", "Must not be empty or negative"));
        expected.add(new CoreError("CategoryID", "Must not be empty or negative"));
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void All_successful() {
        AddProductToCategoryRequest request = new AddProductToCategoryRequest(1, 1);
        Mockito.when(productDatabase.getById(1L)).
                thenReturn(Optional.of(new Product("Audi", "red1234567890", 345)));
        List<CoreError> expected = new ArrayList<>();
        Mockito.when(categoriesDatabase.getCategory(1L)).thenReturn(Optional.of(new Category("Phone")));
        List<CoreError> actual = subject.validate(request);
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isEmpty();
    }


}