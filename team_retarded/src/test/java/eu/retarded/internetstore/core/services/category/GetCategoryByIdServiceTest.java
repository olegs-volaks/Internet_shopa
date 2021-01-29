package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.category.GetCategoryByIdResponse;
import eu.retarded.internetstore.core.services.validators.category.GetCategoryByIdValidator;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


    @ExtendWith(MockitoExtension.class)
    class GetCategoryByIdServiceTest {
        @Mock
        private GetCategoryByIdValidator validator;
        @Mock
        private CategoriesDatabase db;
        @InjectMocks
        private GetCategoryByIdService service;

        @Test
        public void shouldReturnResponseWithErrorsWhenValidatorFails() {
            GetCategoryByIdRequest request = new GetCategoryByIdRequest(-1L);

            List<CoreError> errors = new ArrayList<>();
            errors.add(new CoreError("ID", "Must be more than 0"));


            Mockito.when((validator.validate(request))).thenReturn(errors);

            GetCategoryByIdResponse response = service.execute(request);
            assertTrue(response.hasErrors());
            assertEquals(response.getErrors().size(), 1);
            assertEquals(response.getErrors().get(0).getField(), "ID");
            assertEquals(response.getErrors().get(0).getMessage(), "Must be more than 0");

            Mockito.verify(validator).validate(request);
            Mockito.verify(validator).validate(any());

        }

        @Test
        public void GetProductByIdTest() {
            GetCategoryByIdRequest request = new GetCategoryByIdRequest(1L);
            Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

            Category category = new Category("Title");
            Mockito.when(db.getCategory(1L)).thenReturn(Optional.of(category));

            GetCategoryByIdResponse response = service.execute(request);
            assertFalse(response.hasErrors());
            assertEquals(response.getCategory().getName(), "Title");

        }
    }