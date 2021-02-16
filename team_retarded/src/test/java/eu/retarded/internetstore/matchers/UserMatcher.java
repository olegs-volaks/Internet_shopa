package eu.retarded.internetstore.matchers;

import eu.retarded.internetstore.core.domain.User;
import org.mockito.ArgumentMatcher;

public class UserMatcher implements ArgumentMatcher<User> {

    private final String login;
    private final String password;

    public UserMatcher(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean matches(User argument) {
/*        return argument.getLogin().equals(login) &&
                argument.getPassword().equals(password);*/
        return false;
    }
}
