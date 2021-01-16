package eu.retarded.internetstore.ui.delivery;

import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.DeleteDeliveryResponse;
import eu.retarded.internetstore.core.services.delivery.DeleteDeliveryService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteDeliveryUIAction implements UIAction {

    @Autowired
    DeleteDeliveryService service;

    @Override
    public void execute() {
        long id;
        do {
            id = getId();
        } while (id < 0);
        DeleteDeliveryResponse response = service.execute(new DeleteDeliveryRequest(id));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            if (response.isDeliveryDeleted()) {
                System.out.println("Your delivery was removed from list.");
            } else {
                System.out.println("Your delivery not removed from list.");
            }
        }
    }

    private long getId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter product id: ");
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

