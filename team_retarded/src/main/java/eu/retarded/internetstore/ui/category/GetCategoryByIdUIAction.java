package eu.retarded.internetstore.ui.category;

import eu.retarded.internetstore.core.requests.category.GetCategoryByIdRequest;
import eu.retarded.internetstore.core.responses.category.GetCategoryByIdResponse;
import eu.retarded.internetstore.core.services.category.GetCategoryByIdService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class GetCategoryByIdUIAction implements UIAction {

    @Autowired
    private GetCategoryByIdService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Get category by ID: ");
        long categoryId = Long.parseLong(scanner.nextLine());
        GetCategoryByIdResponse response = service.execute(new GetCategoryByIdRequest(categoryId));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else if (response.getCategory().equals(Optional.empty())) {
            System.out.println("The category with the given id does not exist");
        } else if (response.getCategory().isPresent()) {
            System.out.println("Your category was got by ID");
            System.out.println(response.getCategory().get());
        }
    }
}