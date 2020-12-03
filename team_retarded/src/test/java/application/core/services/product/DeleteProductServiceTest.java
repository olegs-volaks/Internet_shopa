package application.core.services.product;

import application.core.requests.product.DeleteProductRequest;
import application.core.responses.CoreError;
import application.core.responses.product.DeleteProductResponse;
import application.core.services.validators.product.DeleteProductValidator;
import application.database.ProductDatabase;
import application.items.Product;
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
        errors.add(new CoreError("ID","Must not be empty, negative or fractional"));

        Mockito.when((validator.validate(request))).thenReturn(errors);

        DeleteProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(),"ID");

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(db);
    }

    @Test
    public void shouldDeleteBookWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        db.add(new Product("Title1", "Author123456789", 345));
        db.add(new Product("Title2", "Author123456789", 345));
        DeleteProductRequest request = new DeleteProductRequest(2L);
        DeleteProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.idProductDeleted());

    }
}
