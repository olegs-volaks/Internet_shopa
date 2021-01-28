package eu.retarded.internetstore.core.services.validators.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteProductValidatorTest {

    @Mock
    private ProductDatabase productDatabase;

    @InjectMocks
    private DeleteProductValidator subject;

    @Test
    void test1() {
        long id = 1;
        DeleteProductRequest request = new DeleteProductRequest(id);
        Mockito.when(!productDatabase.isExist(request.getProductId())).thenReturn(true);
        List<CoreError> actual = subject.validate(request);
        List<CoreError> expected = new ArrayList<>();
        expected.add(new CoreError("ProductID", "Product with this ID does not exist"));
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotEmpty();
    }

    @Test
    void test2() {
        long id = 1;
        DeleteProductRequest request = new DeleteProductRequest(id);
        List<CoreError> actual = subject.validate(new DeleteProductRequest(0));
        Mockito.when(productDatabase.isExist(request.getProductId())).thenReturn(false);
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(coreError -> coreError.getField().equals("ID") &&
                coreError.getMessage().equals("Must not be empty, negative or fractional"));
    }
}