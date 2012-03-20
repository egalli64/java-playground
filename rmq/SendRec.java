package rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import java.io.IOException;

public class SendRec {
    private final static String QUEUE_NAME = "simple";

    public boolean send() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // default on host

        try {
            Connection connection = factory.newConnection();

            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "A message";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(message);

            channel.basicPublish("", QUEUE_NAME, null, null);
            System.out.println("Empty message");

            channel.close(); // done automatically by Connection.close()
            connection.close();
        } catch (IOException e) {
            System.out.println("Error sending messages: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean receive() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, consumer);

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                byte[] body = delivery.getBody();
                if(body.length == 0) {
                    System.out.println("Empty message received");
                    break;
                }

                String message = new String(body);
                System.out.println(message);
            }

            channel.close();
            connection.close();
        } catch (IOException|InterruptedException e ) {
            System.out.println("Error getting messages: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] argv) {
        SendRec sr = new SendRec();

        System.out.println("Sending");
        if(!sr.send())
            return;

        System.out.println("Receiving");
        sr.receive();
    }
}
