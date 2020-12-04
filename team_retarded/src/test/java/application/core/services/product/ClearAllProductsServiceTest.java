package application.core.services.product;

import application.core.requests.product.ClearAllProductsRequest;
import application.core.responses.CoreError;
import application.core.responses.product.ClearAllProductsResponse;
import application.core.services.validators.product.DeleteProductValidator;
import application.database.ProductDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClearAllProductsServiceTest {

    @Mock
    private ProductDatabase db;

    @InjectMocks
    private ClearAllProductsService subject;

    @Test
    public void shouldDeleteBookWithIdFromDatabase() {


    }

}