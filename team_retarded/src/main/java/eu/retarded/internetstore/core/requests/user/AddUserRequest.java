package eu.retarded.internetstore.core.requests.user;

public class AddUserRequest {

    private final String name;
    private final String password;


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    public AddUserRequest(String name, String password) {
        this.name = name;
        this.password = password;

    }
}
