package eu.retarded.internetstore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan(basePackages = "eu.retarded.internetstore")
@PropertySource(value = "classpath:application.properties")
public class applicationConfiguration {

}
