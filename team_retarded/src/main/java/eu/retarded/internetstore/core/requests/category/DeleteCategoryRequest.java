package eu.retarded.internetstore.core.requests.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteCategoryRequest {

    private final long categoryId;

}
