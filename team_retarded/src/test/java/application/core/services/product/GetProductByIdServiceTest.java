package application.core.services.product;

import application.core.requests.product.GetProductByIdRequest;
import application.core.responses.CoreError;
import application.core.responses.product.GetProductByIdResponse;
import application.core.services.validators.product.GetProductByIdValidator;
import application.database.ProductDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(0).getField(),"ID");
        assertEquals(response.getErrors().get(0).getMessage(),"Must be more than 0");
        assertEquals(response.getErrors().get(1).getMessage(),"The product with the given id does not exist");

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());

    }

   /* @Test
    public void GetProductById() {
        GetProductByIdRequest request = new GetProductByIdRequest(1L);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        Product product=new Product("Title", "Author123456789", 345);
        Mockito.when(db.getById(1L)).thenReturn(Optional.of(product));

        GetProductByIdResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProduct().getName(), "Title");
        assertEquals(response.getProduct().getDescription(), "Author123456789");
    }*/
}
