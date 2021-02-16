package eu.retarded.internetstore.core.requests.category;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@RequiredArgsConstructor
public class ShowAllCategoriesRequest {


    private Pageable pageable;
}
