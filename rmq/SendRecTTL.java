/**
 * RabbitMQ TTL Per-queue Time-To-Live
 * See http://bitingcode.blogspot.com/2012/06/per-queue-message-time-to-live-ttl.html
 */
package rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SendRecTTL {
    private final static String QUEUE_NAME = "ttl";
    private final static int TTL = 500;
    private Map<String, Object> args = new HashMap<>();

    public SendRecTTL() { args.put("x-message-ttl", TTL); }

    private Connection newConnection() throws IOException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        return factory.newConnection();
    }
    
    public boolean send() {

        try {
            Connection connection = newConnection();
            
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, args);
            String message = "A message";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(message);

            channel.basicPublish("", QUEUE_NAME, null, null);
            System.out.println("Empty message");

            connection.close();
        } catch (IOException e) {
            System.out.println("Error sending messages: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean receive() {
        try {
            Connection connection = newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, args);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, consumer);

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery(1000);
                if(delivery == null)
                {
                    System.out.println("Timeout!");
                    break;
                }
                byte[] body = delivery.getBody();
                if(body.length == 0) {
                    System.out.println("Empty message received");
                    break;
                }

                String message = new String(body);
                System.out.println(message);
            }

            connection.close();
        } catch (IOException|InterruptedException e ) {
            System.out.println("Error getting messages: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] argv) {
 
        System.out.println("TTL bigger than delay");
        {
            SendRecTTL sr = new SendRecTTL();

            System.out.println("Sending");
            if(!sr.send())
                return;

            try { Thread.sleep(333); } catch(InterruptedException ie) {}

            System.out.println("Receiving");
            sr.receive();
        }

        System.out.println("TTL less than delay");
        {
            SendRecTTL sr = new SendRecTTL();

            System.out.println("Sending");
            if(!sr.send())
                return;

            try { Thread.sleep(1000); } catch(InterruptedException ie) {}

            System.out.println("Receiving");
            sr.receive();
        }
    }
}
