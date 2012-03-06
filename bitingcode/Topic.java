package rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;

public class Topic {
    private static final String EXCHANGE_NAME = "logDirect";

    private enum Quality { Quick, Lazy, Quiet }
    private enum Color { Blue, Red, Yellow }
    private enum Animal { Elephant, Rabbit, Fox }
    private static final String RK_CONTROL = "Control";

    private void producer() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            for(Quality q: Quality.values())
                for(Color c: Color.values())
                    for(Animal a: Animal.values()) {
                        String key = q.name() + '.' + c.name() + '.' + a.name();
                        channel.basicPublish(EXCHANGE_NAME, key, null, "Hello".getBytes());
                        System.out.println("Sent message using routing key " + key);
                    }
            channel.basicPublish(EXCHANGE_NAME, RK_CONTROL, null, null);
            System.out.println("Control terminator message sent");

            channel.close();
            connection.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void consumer(String topic) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String queueName = channel.queueDeclare().getQueue();

            channel.queueBind(queueName, EXCHANGE_NAME, RK_CONTROL);
            channel.queueBind(queueName, EXCHANGE_NAME, topic);

            System.out.println("Waiting for messages.");

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, true, consumer);

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                byte[] body = delivery.getBody();
                String key = delivery.getEnvelope().getRoutingKey();
                if(key.compareTo(RK_CONTROL) == 0 && body.length == 0) {
                    System.out.println("Control terminator detected.");
                    break;
                }

                String message = new String(body);
                System.out.println("Received " + key + ": " + message);
            }
            channel.close();
            connection.close();
        }
        catch(IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        Topic t = new Topic();

        if(argv.length == 0)
            t.producer();
        else
            t.consumer(argv[0]);
    }
}
