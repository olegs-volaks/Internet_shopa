package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserName(String userName);

    boolean existsByUserName(String userName);

}
