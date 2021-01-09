package eu.retarded.internetstore.ui.user;

import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.user.AddUserResponse;
import eu.retarded.internetstore.core.services.user.AddUserService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUserUIAction implements UIAction {

    @Autowired
    private AddUserService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter new user name: ");
        String name = scanner.nextLine();
        System.out.print("Please, enter new user password: ");
        String password = scanner.nextLine();

        AddUserResponse response = service.execute(new AddUserRequest(name, password));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("User with ID " + response.getUserId() + " was added successfully");
        }
    }


}
