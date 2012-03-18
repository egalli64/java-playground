package amq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

public class Selector {
    private static Logger logger = LoggerFactory.getLogger(Selector.class);
    private static final String QUEUE_NAME = "Selector";
    private static final String PROP_DES = "Description";
    private static final String PROP_PRICE = "Price";

    private Session session;

    private void send(MessageProducer producer, String txt, String des, double price) {
        try {
            TextMessage message = session.createTextMessage();
            message.setText(txt);
            message.setStringProperty(PROP_DES, des);
            message.setDoubleProperty(PROP_PRICE, price);
            producer.send(message);
            logger.info("Sending: {}", message.getText());
        } catch(JMSException e) {
            logger.error("Can't send message {}", txt);
        }
    }

    public void producer() {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageProducer producer = session.createProducer(destination);

            send(producer, "Special offer", "Nails", 42.23);
            send(producer, "Out of stock", "Pins", 12.23);
        } catch(JMSException ex) {
            ex.printStackTrace();
        } finally { try { if(connection != null) connection.close(); } catch (JMSException e) {} }
    }

    private class MyMessageListener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            if(message instanceof TextMessage) {
                try {
                    logger.info("{}: {}", PROP_DES, message.getStringProperty(PROP_DES));
                    logger.info("{}: {}", PROP_PRICE, message.getDoubleProperty(PROP_PRICE));
                    logger.info("Message id: {}", message.getJMSMessageID());
                    logger.info("Message: {} ", ((TextMessage) message).getText());
                } catch (Exception e) {
                    logger.error("Can't extract text from received message", e);
                }
            }
        }
    }

    public void consumer(String arg) {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);

            MessageConsumer consumer;
            String filter;
            switch (arg) {
                case "Nails":
                case "Pins":
                    filter = PROP_DES + " = '" + arg + "'";
                    consumer = session.createConsumer(destination, filter);
                    break;
                case "Cheap":
                    filter = PROP_PRICE + " < 15";
                    consumer = session.createConsumer(destination, filter);
                    break;
                default:
                    consumer = session.createConsumer(destination);
                    break;
            }
            consumer.setMessageListener(new MyMessageListener());

            try{ Thread.sleep(1000); } catch(InterruptedException e) {}
        } catch(JMSException ex) {
            ex.printStackTrace();
        } finally { try { if(connection != null) connection.close(); } catch (JMSException e) {} }
    }

    public static void main(String[] args) {
        Selector sel = new Selector();
        if(args.length > 0) {
            logger.info("Selector consumer");
            sel.consumer(args[0]);
        }
        else {
            logger.info("Selector producer");
            sel.producer();
        }
    }
}
