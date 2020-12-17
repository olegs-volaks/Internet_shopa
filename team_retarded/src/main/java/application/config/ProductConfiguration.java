package application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "application")
//@PropertySource(value = "classpath:application.properties")
public class ProductConfiguration {

}
