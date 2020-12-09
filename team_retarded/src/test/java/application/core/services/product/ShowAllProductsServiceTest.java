package application.core.services.product;

import application.core.requests.product.ShowAllProductsRequest;
import application.core.responses.product.ShowAllProductsResponse;
import application.database.ProductDatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ShowAllProductsServiceTest {

    @Mock
    private ProductDatabase database;
    @InjectMocks
    private ShowAllProductsService subject;

    @Test
    public void should_get_product_from_dataBase() {
        ShowAllProductsResponse response = subject.execute(new ShowAllProductsRequest());
        assertThat(response.hasErrors()).isFalse();
        Mockito.verify(database).getList();

    }
}