package eu.retarded.internetstore.core.requests.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetCategoryByIdRequest {

    private final long categoryId;

}