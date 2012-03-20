package rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import java.io.IOException;

public class Router {
    private static final String EXCHANGE_NAME = "logDirect";
    private enum Severity { Debug, Info, Warning, Error }
    private static final String RK_CONTROL = "Control";

    private void producer() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            for(Severity s: Severity.values()) {
                channel.basicPublish(EXCHANGE_NAME, s.name(), null, "Hello".getBytes());
                System.out.println("Sending message with severity " + s.name());
            }
            channel.basicPublish(EXCHANGE_NAME, RK_CONTROL, null, null);

            channel.close();
            connection.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void consumer(String[] subscriptions) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            String queueName = channel.queueDeclare().getQueue();

            // all consumers subscribe to the control flow
            channel.queueBind(queueName, EXCHANGE_NAME, RK_CONTROL);

            // custom subscriptions
            for(String subscription: subscriptions) {
                boolean matching = false;
                for(Severity sev: Severity.values()) {
                    if(subscription.equalsIgnoreCase(sev.name())) {
                        channel.queueBind(queueName, EXCHANGE_NAME, sev.name());
                        System.out.println("Subscribing to " + sev.name() + " messages");
                        matching = true;
                        break;
                    }
                }
                if(!matching)
                    System.out.println(subscription + " severity not available");
            }

            System.out.println("Waiting for messages.");

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, true, consumer);

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String key = delivery.getEnvelope().getRoutingKey();
                byte[] body = delivery.getBody();
                if(key.compareTo(RK_CONTROL) == 0 && body.length == 0) {
                    System.out.println("Control terminator detected.");
                    break;
                }
                String message = new String(body);

                System.out.println("Routing key: " + key + ", message: " + message);
            }

            channel.close();
            connection.close();
        }
        catch(IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        Router r = new Router();

        if(argv.length == 0)
            r.producer();
        else
            r.consumer(argv);
    }
}
