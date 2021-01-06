package eu.retarded.internetstore.ui;


import org.springframework.stereotype.Component;


@Component
public class ExitUIAction implements MenuUIAction {

    @Override
    public void execute() {
        System.out.println("Good Bye! ");
        System.exit(0);
    }
}
