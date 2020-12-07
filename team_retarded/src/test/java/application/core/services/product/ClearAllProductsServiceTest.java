package application.core.services.product;

import application.core.responses.product.ClearAllProductsResponse;
import application.database.ProductDatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class ClearAllProductsServiceTest {

    @Mock
    private ProductDatabase database;

    @InjectMocks
    private ClearAllProductsService subject;

    @Test
    public void should_clear_all_products() {
        ClearAllProductsResponse response = subject.execute();
        assertThat(response.hasErrors()).isFalse();
        Mockito.verify(database).clear();


    }

}