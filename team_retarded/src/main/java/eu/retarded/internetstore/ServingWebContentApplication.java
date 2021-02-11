package eu.retarded.internetstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
public class ServingWebContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }
}
