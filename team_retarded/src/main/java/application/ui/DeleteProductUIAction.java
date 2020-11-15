package application.ui;
import application.requests.DeleteProductRequests;
import application.responses.DeleteProductResponse;
import application.services.DeleteProductService;
import java.util.Scanner;

public class DeleteProductUIAction  implements UIAction {

    private final DeleteProductService service;

    public DeleteProductUIAction(DeleteProductService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter product id to remove:  ");
        long productId = Long.parseLong(scanner.nextLine());
        service.deleteProduct(productId);
        DeleteProductRequests requests = new DeleteProductRequests(productId);
        DeleteProductResponse response = service.execute(requests);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isDeleteProductId()) {
                System.out.println("Your product was successfully removed.");
            } else{
                System.out.println("Your product not removed from list.");
            }
        }
    }
}
