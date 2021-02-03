package eu.retarded.internetstore.ui.delivery;

import eu.retarded.internetstore.core.requests.delivery.GetByIdDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.GetByIdDeliveryResponse;
import eu.retarded.internetstore.core.services.delivery.GetByIdDeliveryService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetByIdDeliveryUIAction implements UIAction {

    @Autowired
    GetByIdDeliveryService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Get delivery by ID: ");
        long deliveryId = Long.parseLong(scanner.nextLine());
        GetByIdDeliveryResponse response = service.execute(new GetByIdDeliveryRequest(deliveryId));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - " +
                    coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("Your delivery was got by ID");
        }
    }
}
