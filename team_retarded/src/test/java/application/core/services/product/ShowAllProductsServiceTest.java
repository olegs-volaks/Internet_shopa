package application.core.services.product;

import application.core.requests.product.ShowAllProductsRequest;
import application.core.responses.product.ShowAllProductsResponse;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@ExtendWith(MockitoExtension.class)
public class ShowAllProductsServiceTest {

    @Mock
    private ProductDatabase db;
    @InjectMocks
    private ShowAllProductsService subject;

    @Test
    public void shouldGetProductFromDataBase() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Name", "Description", 0));
        Mockito.when(db.getList()).thenReturn(products);

        ShowAllProductsRequest request = new ShowAllProductsRequest();
        ShowAllProductsResponse response = subject.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Name");
        assertEquals(response.getProducts().get(0).getDescription(), "Description");

    }
}