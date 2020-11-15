package application.ui;
import application.requests.ShowProductByIDRequests;
import application.responses.ShowProductByIDResponse;
import application.services.ShowProductByIDService;
import java.util.Scanner;


public class ShowProductByIDAction implements UIAction {

    private final ShowProductByIDService service;

    public ShowProductByIDAction(ShowProductByIDService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter product ID: ");
        long productId = Long.parseLong(scanner.nextLine());
        service.getById(productId);
        ShowProductByIDRequests requests = new ShowProductByIDRequests();
        ShowProductByIDResponse response = service.execute(requests);
       // response.getById().forEach()

        System.out.println("Your product was successfully found . ");

    }
}
