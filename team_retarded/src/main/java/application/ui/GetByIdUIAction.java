package application.ui;


import application.services.GetByIdService;


import java.util.Scanner;


public class GetByIdUIAction implements UIAction {

    private final GetByIdService service;

    public GetByIdUIAction(GetByIdService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Get product by ID: ");
        long productId = Long.parseLong(scanner.nextLine());
        if (service.getById(productId).isEmpty()) {
            System.out.println("Your product not exist");
        } else {
            System.out.println("Your product was got by ID");
            System.out.println(service.getById(productId).get());
        }
    }
}
