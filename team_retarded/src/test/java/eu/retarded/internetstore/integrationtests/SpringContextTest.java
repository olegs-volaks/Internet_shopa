package eu.retarded.internetstore.integrationtests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class SpringContextTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void start() {
        assertNotNull(context);
    }

}
