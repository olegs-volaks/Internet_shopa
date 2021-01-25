package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.DeleteAllCategoryRequest;
import eu.retarded.internetstore.core.responses.category.DeleteAllCategoryResponse;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DeleteAllCategoryServiceTest {

    @Mock
    private CategoriesDatabase database;
    @InjectMocks
    private DeleteAllCategoryService subject;

    @Test
    public void should_delete_all_categories() {
        DeleteAllCategoryResponse response = subject.execute(new DeleteAllCategoryRequest());
        assertThat(response.hasErrors()).isFalse();
        Mockito.verify(database).clear();
    }


}