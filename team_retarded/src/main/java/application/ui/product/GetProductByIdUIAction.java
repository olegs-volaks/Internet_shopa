package application.ui.product;


import application.core.requests.product.GetProductByIdRequest;
import application.core.responses.product.GetProductByIdResponse;
import application.core.services.product.GetProductByIdService;
import application.ui.UIAction;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.Optional;
import java.util.Scanner;

@DIComponent
public class GetProductByIdUIAction implements UIAction {

    @DIDependency
    private GetProductByIdService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Get product by ID: ");
        long productId = Long.parseLong(scanner.nextLine());
        GetProductByIdResponse response = service.execute(new GetProductByIdRequest(productId));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else if(response.getProduct().equals(Optional.empty())){
            System.out.println("The product with the given id does not exist");
        }else if(response.getProduct().isPresent()){
            System.out.println("Your product was got by ID");
            System.out.println(response.getProduct().get());
        }
    }
}
