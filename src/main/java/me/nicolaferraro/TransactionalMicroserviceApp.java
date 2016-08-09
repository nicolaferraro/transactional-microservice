package me.nicolaferraro;

import org.apache.camel.spring.boot.FatJarRouter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 *
 */
@SpringBootApplication
@ImportResource("amq.xml")
public class TransactionalMicroserviceApp extends FatJarRouter {

    public static void main(String[] args) {
        FatJarRouter.main(args);
    }

    @Override
    public void configure() throws Exception {

        from("timer:tick").id("Sender").setBody().constant("Hello").log("Sending ${body} to JMS broker").to("amq:queue:sourcequeue");

        from("amq:queue:sourcequeue").id("Receiver").transacted().log("Received ${body} from JMS broker").to("amq:queue:anotherqueue");

    }
}
