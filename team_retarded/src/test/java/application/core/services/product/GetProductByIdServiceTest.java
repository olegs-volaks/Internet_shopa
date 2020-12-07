package application.core.services.product;

import application.core.requests.product.GetProductByIdRequest;
import application.core.responses.CoreError;
import application.core.responses.product.GetProductByIdResponse;
import application.core.services.validators.product.GetProductByIdValidator;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class GetProductByIdServiceTest {
    @Mock
    private GetProductByIdValidator validator ;
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
        assertEquals(response.getErrors().get(0).getField(),"ID");
        assertEquals(response.getErrors().get(0).getMessage(),"Must be more than 0");

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());

    }

    @Test
    public void GetProductByIdTest() {
        GetProductByIdRequest request = new GetProductByIdRequest(1L);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Product product=new Product("Title", "Author123456789", 345);
        Mockito.when(db.getById(1L)).thenReturn(Optional.of(product));

        GetProductByIdResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProduct().get().getName(), "Title");
        assertEquals(response.getProduct().get().getDescription(), "Author123456789");
    }
}
