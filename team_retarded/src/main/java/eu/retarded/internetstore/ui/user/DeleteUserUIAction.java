package eu.retarded.internetstore.ui.user;

import eu.retarded.internetstore.core.requests.user.DeleteUserRequest;
import eu.retarded.internetstore.core.responses.user.DeleteUserResponse;
import eu.retarded.internetstore.core.services.user.DeleteUserService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteUserUIAction implements UIAction {

    @Autowired
    private DeleteUserService service;

    @Override
    public void execute() {
        long id;
        do {
            id = getId();
        } while (id < 0);
        DeleteUserResponse response = service.execute(new DeleteUserRequest(id));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            if (response.isDeleted()) {
                System.out.println("User was removed from list.");
            } else {
                System.out.println("User not removed from list.");
            }
        }

    }

    private long getId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter user id: ");
        String id = scanner.nextLine();
        id = id.replaceAll("\\s+", "");
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }

}