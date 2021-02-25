package eu.retarded.internetstore.core.requests.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchProductRequest {

    @NotBlank(message = "The keyWord must not be empty!")
    private String keyWord;

    private Pageable pageable;

}
