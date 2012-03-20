package rmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Escher {
    static private final String HOST = "127.0.0.1";
    static private final int PORT = 6391; // default is 5672
    static private final String V_HOST = "test";
    static private final String USER = "user";
    static private final String PASSWORD = "password";

    static private final String EXC_NAME = "ExTest";
    static private final String EXC_TYPE = "direct";
    static private final String R_KEY = "RoutingKey";

    private ConnectionFactory getFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setVirtualHost(V_HOST);
        factory.setUsername(USER);
        factory.setPassword(PASSWORD);

        System.out.println("Starting on " + factory.getHost() + ':' + factory.getPort() + " ["
                + factory.getVirtualHost() + "] for " + factory.getUsername());
        return factory;
    }

    public void producer() {
        ConnectionFactory factory = getFactory();

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXC_NAME, EXC_TYPE);
            channel.basicPublish(EXC_NAME, R_KEY, null, "Hello, rabbit.".getBytes()) ;
            channel.close();
            connection.close();
            System.out.println("Producer has sent its message");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void consumer() {
        ConnectionFactory factory = getFactory();

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXC_NAME, EXC_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXC_NAME, R_KEY);
            
            System.out.println("Waiting for a message");

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queue, true, consumer);

            // just one message
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            System.out.println("Received: " + new String(delivery.getBody()));

            channel.close();
            connection.close();
        }
        catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Escher escher = new Escher();

        if(args.length == 0)
            escher.producer();
        else
            escher.consumer();
    }
}
