package eu.retarded.internetstore.core.requests.user;

public class AddUserRequest {

    private final String login;
    private final String password;


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public AddUserRequest(String login, String password) {
        this.login = login;
        this.password = password;

    }
}
