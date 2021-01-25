package eu.retarded.internetstore;

import eu.retarded.internetstore.config.ApplicationConfiguration;
import eu.retarded.internetstore.ui.ProgramMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = createApplicationContext();
        ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);

        while (true) {
            programMenu.print();
            int menuNumber = programMenu.getMenuNumberFromUser();
            if (menuNumber == -1) {
                continue;
            }
            programMenu.executeSelectedMenuItem(menuNumber);
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    }
}
