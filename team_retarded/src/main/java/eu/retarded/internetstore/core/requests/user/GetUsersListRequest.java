package eu.retarded.internetstore.core.requests.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;


@Getter
@RequiredArgsConstructor
public class GetUsersListRequest {

    private Pageable pageable;
}
