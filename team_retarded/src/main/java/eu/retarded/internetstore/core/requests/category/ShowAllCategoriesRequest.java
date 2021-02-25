package eu.retarded.internetstore.core.requests.category;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowAllCategoriesRequest {

    private Pageable pageable;
}
