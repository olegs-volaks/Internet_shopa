package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.responses.product.ClearAllProductsResponse;
import eu.retarded.internetstore.database.ProductDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ClearAllProductsServiceTest {

    @Mock
    private ProductDatabase database;

    @InjectMocks
    private ClearAllProductsService subject;

    @Test
    public void should_clear_all_products() {
        ClearAllProductsResponse response = subject.execute();
        Assertions.assertThat(response.hasErrors()).isFalse();
        Mockito.verify(database).clear();


    }

}