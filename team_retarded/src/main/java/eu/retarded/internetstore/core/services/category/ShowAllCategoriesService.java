package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.requests.category.ShowAllCategoriesRequest;
import eu.retarded.internetstore.core.responses.category.ShowAllCategoriesResponse;
import eu.retarded.internetstore.database.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ShowAllCategoriesService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Validator validator;

    @Transactional
    public ShowAllCategoriesResponse execute(ShowAllCategoriesRequest request) {
        Set<ConstraintViolation<ShowAllCategoriesRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ShowAllCategoriesResponse(errors);
        }
        List<Category> categories;
        if (request.getPageable() == null) {
            categories = categoryRepository.findAll();
            return new ShowAllCategoriesResponse(null, categories);
        }
        Page<Category> categoryPage=categoryRepository.findAll(request.getPageable());
        return new ShowAllCategoriesResponse(categoryPage, categoryPage.toList());
    }
}

