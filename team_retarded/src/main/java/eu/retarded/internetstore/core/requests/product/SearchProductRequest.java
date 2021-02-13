package eu.retarded.internetstore.core.requests.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Getter
@RequiredArgsConstructor
public class SearchProductRequest {

    @NotBlank(message = "The keyWord must not be empty!")
    private String keyWord;

    @NotBlank(message = "Sorting must not be empty!")
    @Pattern(regexp ="<ASC>|<DESC>",message = "The sorting must be ASC or DESC")
    private String sorting;

    @PositiveOrZero(message = "The page must be positive or zero")
    private int page;
}
