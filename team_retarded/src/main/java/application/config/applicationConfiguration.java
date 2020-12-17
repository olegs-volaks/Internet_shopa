package application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan(basePackages = "application")
@PropertySource(value = "classpath:application.properties")
public class applicationConfiguration {

}
