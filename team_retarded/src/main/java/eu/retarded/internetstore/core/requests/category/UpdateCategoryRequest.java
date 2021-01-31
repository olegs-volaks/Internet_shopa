package eu.retarded.internetstore.core.requests.category;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateCategoryRequest {
    private final long id;
    private final String name;
}
