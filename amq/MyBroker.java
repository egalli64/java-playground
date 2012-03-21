package amq;

import org.apache.activemq.broker.BrokerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyBroker {
    private static final String QUEUE_NAME = "Hello";
    private static Logger logger = LoggerFactory.getLogger(MyBroker.class);

    public static void main(String[] args) {
        BrokerService broker = new BrokerService();
        broker.setBrokerName("myBroker");
        broker.setDataDirectory("data/");

        try {
            broker.addConnector("tcp://localhost:61616");
            broker.start();
        } catch (Exception ex) {
            logger.error("Can't start broker", ex);
        }

        logger.info("ActiveMQ broker is up and running");

        try{
            System.out.println(" *** Enter to shutdown ***");
            System.in.read();
            logger.info("Shutting down ActiveMQ broker");
            broker.stop();
        } catch(Exception ex) {
            logger.error("Error stopping broker", ex);
        }
    }
}
