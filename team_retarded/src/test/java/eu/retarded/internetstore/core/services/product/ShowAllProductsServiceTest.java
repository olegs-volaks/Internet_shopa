package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.product.ShowAllProductsResponse;
import eu.retarded.internetstore.database.ProductDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ShowAllProductsServiceTest {

    @Mock
    private ProductDatabase database;
    @InjectMocks
    private ShowAllProductsService subject;

    @Test
    public void should_get_product_from_dataBase() {
        ShowAllProductsResponse response = subject.execute(new ShowAllProductsRequest());
        Assertions.assertThat(response.hasErrors()).isFalse();
        Mockito.verify(database).getList();

    }
}