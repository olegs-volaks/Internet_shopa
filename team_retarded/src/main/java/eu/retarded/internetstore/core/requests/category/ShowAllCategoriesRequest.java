package eu.retarded.internetstore.core.requests.category;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class ShowAllCategoriesRequest {

    @NotBlank(message = "Pageable must not be empty!")
    private Pageable pageable;
}
