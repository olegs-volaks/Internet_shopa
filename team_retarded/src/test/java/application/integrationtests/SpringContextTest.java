package application.integrationtests;


import application.config.applicationConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {applicationConfiguration.class})
public class SpringContextTest {

    @Autowired private ApplicationContext context;

    //@Test
    //public void start() {
        //assertNotNull(context);
   // }

}
