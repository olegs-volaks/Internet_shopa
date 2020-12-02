package application.core.services.product;

import application.core.requests.product.SearchProductRequest;
import application.core.responses.CoreError;
import application.core.responses.product.SearchProductResponse;
import application.core.services.validators.product.SearchProductValidator;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class SearchProductServiceTest {
    @Mock
    private SearchProductValidator validator ;
    @Mock
    private ProductDatabase db;
    @InjectMocks
    private SearchProductService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchProductRequest request = new SearchProductRequest(null, null);

        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("name",        "Must not be empty!"));
        errors.add(new CoreError("description", "Must not be empty!"));
        Mockito.when((validator.validate(request))).thenReturn(errors);

        SearchProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 2);
        assertEquals(response.getErrors().get(0).getField(),"name");

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(db);
    }

    @Test
    public void shouldSearchByTitle() {
        SearchProductRequest request = new SearchProductRequest("Title", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Title", "Author123456789", 345));

        Mockito.when(db.filter(any())).thenReturn(products);

        SearchProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Title");
        assertEquals(response.getProducts().get(0).getDescription(), "Author123456789");
    }

    /*@Test
    public void shouldSearchByAuthor() {
        SearchProductRequest request = new SearchProductRequest(null, "Author");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Product> products = new ArrayList<>();
        products.add(new Product("Title", "Author123456789", 345));
        Mockito.when(db.filter(product -> product.getDescription().contains("Author"))).thenReturn(products);

        SearchProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProducts().size(), 1);
        assertEquals(response.getProducts().get(0).getName(), "Title");
        assertEquals(response.getProducts().get(0).getDescription(), "Author123456789");
    }

    /*@Test
    public void shouldSearchByTitleAndAuthor() {
        SearchBooksRequest request = new SearchBooksRequest("Title", "Author");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author"));
        Mockito.when(database.findByTitleAndAuthor("Title", "Author")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 1);
        assertEquals(response.getBooks().get(0).getTitle(), "Title");
        assertEquals(response.getBooks().get(0).getAuthor(), "Author");
    }

    @Test
    public void shouldSearchByTitleWithOrderingAscending() {
        Ordering ordering = new Ordering("author", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("Title", null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author2"));
        books.add(new Book("Title", "Author1"));
        Mockito.when(database.findByTitle("Title")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 2);
        assertEquals(response.getBooks().get(0).getAuthor(), "Author1");
        assertEquals(response.getBooks().get(1).getAuthor(), "Author2");
    }

    @Test
    public void shouldSearchByTitleWithOrderingDescending() {
        Ordering ordering = new Ordering("author", "DESCENDING");
        SearchBooksRequest request = new SearchBooksRequest("Title", null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author1"));
        books.add(new Book("Title", "Author2"));
        Mockito.when(database.findByTitle("Title")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 2);
        assertEquals(response.getBooks().get(0).getAuthor(), "Author2");
        assertEquals(response.getBooks().get(1).getAuthor(), "Author1");
    }

    @Test
    public void shouldSearchByTitleWithPagingFirstPage() {
        Paging paging = new Paging(1, 1);
        SearchBooksRequest request = new SearchBooksRequest("Title", null, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author1"));
        books.add(new Book("Title", "Author2"));
        Mockito.when(database.findByTitle("Title")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 1);
        assertEquals(response.getBooks().get(0).getTitle(), "Title");
        assertEquals(response.getBooks().get(0).getAuthor(), "Author1");
    }

    @Test
    public void shouldSearchByTitleWithPagingSecondPage() {
        Paging paging = new Paging(2, 1);
        SearchBooksRequest request = new SearchBooksRequest("Title", null, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author1"));
        books.add(new Book("Title", "Author2"));
        Mockito.when(database.findByTitle("Title")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 1);
        assertEquals(response.getBooks().get(0).getTitle(), "Title");
        assertEquals(response.getBooks().get(0).getAuthor(), "Author2");
    }*/




}