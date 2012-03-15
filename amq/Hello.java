package amq;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import org.slf4j.*;

public class Hello {
    private static Logger logger = LoggerFactory.getLogger(Hello.class);
    private static final String QUEUE_NAME = "Hello";

    public void producer(String arg) {
        ConnectionFactory factory = new ActiveMQConnectionFactory(); // default broker, on localhost:61616
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // no transaction
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(arg);

            producer.send(message);
            logger.info("Sending: {}", message.getText());
        } catch(JMSException ex) {
            ex.printStackTrace();
        } finally { try { if(connection != null) connection.close(); } catch (JMSException e) {} }
    }

    public void consumer() {
        ConnectionFactory factory = new ActiveMQConnectionFactory(); // default broker, on localhost:61616
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive(1000);
            if(message == null)
                logger.info("No pending message on {} queue.", QUEUE_NAME);
            else if(message instanceof TextMessage)
                logger.info("Received: {}", ((TextMessage)message).getText());
            else
                logger.info("Unexpected non-text message consumed.");
        } catch(JMSException ex) {
            ex.printStackTrace();
        } finally { try { if(connection != null) connection.close(); } catch (JMSException e) {} }
    }

    private class MyMessageListener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            if(message instanceof TextMessage) {
                try {
                    logger.info("Received {} ", ((TextMessage) message).getText());
                } catch (JMSException e) {
                    logger.error("Can't extract text from received message", e);
                }
            }
            else
                logger.info("Unexpected non-text message received.");
        }
    }

    public void asynchronousConsumer() {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MyMessageListener());
            
            try{ Thread.sleep(1000); } catch(InterruptedException e) {}
        } catch(JMSException ex) {
            ex.printStackTrace();
        } finally { try { if(connection != null) connection.close(); } catch (JMSException e) {} }
    }

    public static void main(String[] args) {
        Hello hello = new Hello();
        if(args.length > 0) {
            logger.info("Hello in producer mode");
            hello.producer(args[0]);
        }
        else {
            logger.info("Hello in consumer mode");
//            hello.consumer();
            hello.asynchronousConsumer();
        }
    }
}
