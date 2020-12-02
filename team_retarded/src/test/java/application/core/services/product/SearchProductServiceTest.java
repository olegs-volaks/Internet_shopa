package application.core.services.product;

import application.core.requests.product.SearchProductRequest;
import application.core.responses.CoreError;
import application.core.responses.product.SearchProductResponse;
import application.core.services.validators.product.SearchProductValidator;
import application.database.ListProductDatabase;
import application.database.ProductDatabase;
import application.database.categories.database.CategoriesDatabase;
import application.database.categories.database.ListCategoriesDatabase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@RunWith(MockitoJUnitRunner.class)
class SearchProductServiceTest {
    //private SearchProductValidator validator;
    //private  SearchProductService service;
   // @Before
            //public void setup(){
        //validator= Mockito.mock(SearchProductValidator.class);
        //service =new SearchProductService(null, validator);
    //}


   // @Mock
    //private SearchProductValidator validator ;
    //@Mock
    //private ProductDatabase dbActual;
    //@InjectMocks
    ///private SearchProductService service;

    CategoriesDatabase categoriesDatabase = new ListCategoriesDatabase();
    ProductDatabase dbActual = new ListProductDatabase( categoriesDatabase);
    SearchProductValidator validator = new SearchProductValidator();
    SearchProductService service= new SearchProductService(dbActual,validator);

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchProductRequest request = new SearchProductRequest(null, null);

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("name",        "Must not be empty!"));
        errors.add(new CoreError("description", "Must not be empty!"));
        //Mockito.when((validator.validate(request))).thenReturn(errors);

        SearchProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(0).getField(),"name");

        //Mockito.verify(validator).validate(request);
        //Mockito.verify(validator).validate(any());
        //Mockito.verifyNoInteractions(dbActual);
    }



}