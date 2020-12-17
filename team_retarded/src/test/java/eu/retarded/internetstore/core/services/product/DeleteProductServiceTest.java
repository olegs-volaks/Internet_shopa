package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.DeleteProductResponse;
import eu.retarded.internetstore.core.services.validators.product.DeleteProductValidator;
import eu.retarded.internetstore.database.ProductDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DeleteProductServiceTest {
    @Mock
    private DeleteProductValidator validator;
    @Mock
    private ProductDatabase db;
    @InjectMocks
    private DeleteProductService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        DeleteProductRequest request = new DeleteProductRequest(0);

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID", "Must not be empty, negative or fractional"));

        Mockito.when((validator.validate(request))).thenReturn(errors);

        DeleteProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "ID");

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(db);
    }

    @Test
    public void shouldDeleteProductWithIdFromDatabase() {
        DeleteProductRequest request = new DeleteProductRequest(1L);
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(db.delete(1L)).thenReturn(true);
        DeleteProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isProductDeleted());
    }
}
