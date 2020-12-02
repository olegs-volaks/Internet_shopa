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
import static org.mockito.ArgumentMatchers.argThat;


@ExtendWith(MockitoExtension.class)
public class AddProductServiceTest {

    @Mock private ProductDatabase db;
    @Mock private AddProductValidator validator;
    @InjectMocks private  AddProductService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddProductRequest request = new AddProductRequest("nam","description",225.5);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name","Must be between 4 and 100 characters!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"Name");
        assertEquals(response.getErrors().get(0).getMessage(),"Must be between 4 and 100 characters!");

        Mockito.verifyNoInteractions(db);
    }


   /* @Test
    public void shouldAddProductToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddProductRequest request = new AddProductRequest("Name","Author",222.2);
        AddProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(db).add(                // sdesj ne idet add i save toze,prosit sozdatj metod v productDatabase
                argThat(new ProductMatcher("Name","description")));
    }

    */
}