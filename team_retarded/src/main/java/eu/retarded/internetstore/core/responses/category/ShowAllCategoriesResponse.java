package eu.retarded.internetstore.core.responses.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.ShowAllCategoriesRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class ShowAllCategoriesResponse extends CoreResponse<ShowAllCategoriesRequest> {

    private final Page <Category> categoriesPage;
    private final List <Category> categoriesList;

    public ShowAllCategoriesResponse(Set<ConstraintViolation<ShowAllCategoriesRequest>> errors,
                                             Page<Category> categoriesPage, List<Category> categoriesList) {
        super(errors);
        this.categoriesPage = categoriesPage;
        this.categoriesList = categoriesList;
    }

    public Page <Category> getCategoriesPage() {
        return categoriesPage;
    }
    public List <Category> getCategoriesList() {
        return categoriesList;
    }
}