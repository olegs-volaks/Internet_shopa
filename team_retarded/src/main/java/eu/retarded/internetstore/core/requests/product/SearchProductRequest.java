package eu.retarded.internetstore.core.requests.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class SearchProductRequest {

    @NotBlank(message = "The keyWord must not be empty!")
    private String keyWord;

    private final Pageable pageable;

}
