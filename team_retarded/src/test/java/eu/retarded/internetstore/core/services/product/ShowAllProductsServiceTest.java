package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.product.ShowAllProductsResponse;
import eu.retarded.internetstore.core.services.validators.product.ShowAllProductsValidator;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class ShowAllProductsServiceTest {

    @Mock
    private ProductDatabase database;
    @Mock
    private ShowAllProductsValidator validator;

    @InjectMocks
    private ShowAllProductsService subject;

    @Test
    public void should_get_product_from_dataBase() {
        Paging paging = new Paging(2, 2);
        Ordering ordering = new Ordering("name", "DESCENDING");
        ShowAllProductsRequest request = new ShowAllProductsRequest();
        ShowAllProductsResponse response = subject.execute(request);
        List<CoreError> errors = new ArrayList<>();
        //errors.add(new CoreError("name", "Must not be empty!"));
        //errors.add(new CoreError("description", "Must not be empty!"));

        //Mockito.when((validator.validate(request))).thenReturn(errors);
        Assertions.assertThat(response.hasErrors()).isFalse();
        Mockito.verify(database).getList();

    }

    @Test
    public void should_get_product_from_dataBaseIfAllIsEmpty() {
        Paging paging = new Paging(null, null);
        Ordering ordering = new Ordering(null, null);
        ShowAllProductsRequest request = new ShowAllProductsRequest();
        ShowAllProductsResponse response = subject.execute(request);
        List<CoreError> errors = new ArrayList<>();
        //errors.add(new CoreError("name", "Must not be empty!"));
        //errors.add(new CoreError("description", "Must not be empty!"));

        //Mockito.when((validator.validate(request))).thenReturn(errors);
        Assertions.assertThat(response.hasErrors()).isFalse();
        Mockito.verify(database).getList();

    }
}