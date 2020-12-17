package eu.retarded.internetstore.ui.product;


import eu.retarded.internetstore.ui.UIAction;
import org.springframework.stereotype.Component;


@Component
public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Good Bye! ");
        System.exit(0);
    }
}
