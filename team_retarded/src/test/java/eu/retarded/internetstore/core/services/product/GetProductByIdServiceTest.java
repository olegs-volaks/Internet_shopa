package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.GetProductByIdResponse;
import eu.retarded.internetstore.core.services.validators.product.GetProductByIdValidator;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class GetProductByIdServiceTest {
    @Mock
    private GetProductByIdValidator validator;
    @Mock
    private ProductDatabase db;
    @InjectMocks
    private GetProductByIdService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        GetProductByIdRequest request = new GetProductByIdRequest(-1);

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID", "Must be more than 0"));


        Mockito.when((validator.validate(request))).thenReturn(errors);

        GetProductByIdResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "ID");
        assertEquals(response.getErrors().get(0).getMessage(), "Must be more than 0");

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());

    }

    @Test
    public void GetProductByIdTest() {
        GetProductByIdRequest request = new GetProductByIdRequest(1L);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Product product = new Product("Title", "Author123456789", 345);
        Mockito.when(db.getById(1L)).thenReturn(Optional.of(product));

        GetProductByIdResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProduct().get().getName(), "Title");
        assertEquals(response.getProduct().get().getDescription(), "Author123456789");
    }
}
