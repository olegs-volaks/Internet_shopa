package eu.retarded.internetstore.core.requests.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetUserListRequest {

    private Pageable pageable;
}
