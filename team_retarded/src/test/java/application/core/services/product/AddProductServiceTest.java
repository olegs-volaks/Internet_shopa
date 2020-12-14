package application.core.services.product;

import application.core.requests.product.AddProductRequest;
import application.core.responses.CoreError;
import application.core.responses.product.AddProductResponse;
import application.core.services.validators.product.AddProductValidator;
import application.database.ProductDatabase;
import application.matchers.ProductMatcher;
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
import static org.mockito.ArgumentMatchers.argThat;


@ExtendWith(MockitoExtension.class)
public class AddProductServiceTest {

    @Mock
    private ProductDatabase database;
    @Mock
    private AddProductValidator validator;
    @InjectMocks
    private AddProductService subject;

    @Test
    public void should_return_response_with_errors_when_validation_fails() {
        AddProductRequest request = new AddProductRequest("nam", "description", 225.5);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Name", "Must be between 4 and 10 characters"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddProductResponse response = subject.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must be between 4 and 10 characters");
        Mockito.verifyNoInteractions(database);
        Mockito.verify(validator).validate(request);

    }


    @Test
    public void should_add_product_to_database() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddProductRequest request = new AddProductRequest("Name", "Description", 222.2);
        AddProductResponse response = subject.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(database).add(argThat(new ProductMatcher("Name","Description")));


    }
}