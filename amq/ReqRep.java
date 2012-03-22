package amq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jms.*;
import java.util.UUID;

public class ReqRep {
    private static Logger logger = LoggerFactory.getLogger(ReqRep.class);
    private static final String REQUEST_QUEUE_NAME = "Request";

    public void requester(String arg) {
        // connect to the broker
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // create and send
            Destination destination = session.createQueue(REQUEST_QUEUE_NAME);
            MessageProducer producer = session.createProducer(destination);
            Destination dRep = session.createTemporaryQueue();

            TextMessage message = session.createTextMessage(arg);
            message.setJMSCorrelationID(UUID.randomUUID().toString());
            message.setJMSReplyTo(dRep);
            producer.send(message);

            logger.info("Message sent");

            // wait for replies
            MessageConsumer consumer = session.createConsumer(dRep);
            Message reply = consumer.receive();
            if(reply instanceof TextMessage) {
                logger.info("Reply received: {}", ((TextMessage) reply).getText());
            }
        } catch(JMSException ex) {
            ex.printStackTrace();
        } finally { try { if(connection != null) connection.close(); } catch (JMSException e) {} }
    }

    public void replier() {
        // connect to the broker
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(REQUEST_QUEUE_NAME);

            // consume a message
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive();
            if(message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                logger.info("Message received: {}", text);

                // reply to the requester
                TextMessage answer = session.createTextMessage();
                answer.setText("Reply to " + text);
                answer.setJMSCorrelationID(message.getJMSCorrelationID());

                MessageProducer producer = session.createProducer(null);
                producer.send(message.getJMSReplyTo(), answer);
            }
        } catch(JMSException ex) {
            ex.printStackTrace();
        } finally { try { if(connection != null) connection.close(); } catch (JMSException e) {} }
    }

    public static void main(String[] args) {
        ReqRep rr = new ReqRep();
        if(args.length > 0) {
            logger.info("Requester");
            rr.requester(args[0]);
        }
        else {
            logger.info("Replier");
            rr.replier();
        }
    }
}
