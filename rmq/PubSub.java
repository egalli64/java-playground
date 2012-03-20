package rmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class PubSub {
    private static final String EXCHANGE_NAME = "logs"; // using exchange

    public void producer() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout"); // creating a fanout exchange
            // rabbitmqctl list_exchanges
            // default exchange is nameless ("")

            System.out.println("Sending a message");
            channel.basicPublish(EXCHANGE_NAME, "", null, "a message".getBytes()); // publishing to an exchange
            System.out.println("Sending an empty message");
            channel.basicPublish(EXCHANGE_NAME, "", null, null);

            channel.close();
            connection.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void consumer() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            String myQueue = channel.queueDeclare().getQueue(); // non-durable, exclusive, autodelete queue, random unique name
            channel.queueBind(myQueue, EXCHANGE_NAME, ""); // binding a queue to an exchange
            // rabbitmqctl list_bindings

            System.out.println("Consumer waiting for messages.");
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(myQueue, true, consumer);

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                byte[] body = delivery.getBody();
                if(body.length == 0){
                    System.out.println("Empty message received: terminating.");
                    break;
                }

                String message = new String(body);
                System.out.println("Received: " + message);
            }

            channel.close();
            connection.close();
        }
        catch(IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        PubSub ps = new PubSub();

        if(argv.length == 0)
            ps.producer();
        else
            ps.consumer();
    }
}
