package eu.retarded.internetstore.core.services.category;

import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
import eu.retarded.internetstore.core.responses.category.GetCategoryByIdResponse;
import eu.retarded.internetstore.database.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class GetCategoryByIdService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Validator validator;

    @Transactional
    public GetCategoryByIdResponse execute(GetCategoryByIdRequest request) {
        Set<ConstraintViolation<GetCategoryByIdRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetCategoryByIdResponse(errors);
        }
        return new GetCategoryByIdResponse(categoryRepository.findById(request.getCategoryId()).get());
    }
}

