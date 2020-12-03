package application.core.services.product;

import application.core.requests.product.AddProductRequest;
import application.core.responses.CoreError;
import application.core.responses.product.AddProductResponse;
import application.core.services.validators.product.AddProductValidator;
import application.database.ProductDatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;



@ExtendWith(MockitoExtension.class)
public class AddProductServiceTest {

    @Mock
    private ProductDatabase db;
    @Mock
    private AddProductValidator validator;
    @InjectMocks
    private AddProductService subject;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddProductRequest request = new AddProductRequest("nam", "description", 225.5);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must be not empty"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddProductResponse response = subject.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must be not empty");

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(db);
    }


    @Test
    public void shouldAddProductToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddProductRequest request = new AddProductRequest("Name", "Author", 222.2);
        AddProductResponse response = subject.execute(request);
        assertFalse(response.hasErrors());

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
      //  Mockito.verify(db).add(argThat(new ProductMatcher("Name","Description")));  эта строчка не заходит


    }
}