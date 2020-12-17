package eu.retarded.internetstore.integrationtests;


import eu.retarded.internetstore.config.applicationConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {applicationConfiguration.class})
public class SpringContextTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void start() {
        assertNotNull(context);
    }

}
