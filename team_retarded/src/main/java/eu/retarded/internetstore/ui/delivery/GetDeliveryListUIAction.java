package eu.retarded.internetstore.ui.delivery;

import eu.retarded.internetstore.core.requests.delivery.GetDeliveryListRequest;
import eu.retarded.internetstore.core.responses.delivery.GetDeliveryListResponse;
import eu.retarded.internetstore.core.services.delivery.GetDeliveryListService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class GetDeliveryListUIAction  implements UIAction {

    @Autowired
    GetDeliveryListService service;

    @Override
    public void execute() {
        GetDeliveryListResponse response = service.execute(new GetDeliveryListRequest());
        if (!response.hasErrors()) {
            System.out.println("All delivery: ");
            response.getDeliveries().forEach(System.out::println);
        }
    }
}
