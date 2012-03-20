package rmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Acknowledger {
    private static final String QUEUE_NAME = "ack";

    public void producer() {
        ConnectionFactory factory = new ConnectionFactory();

        try
        {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String message = "Ack me!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Message \"" + message + "\" produced.");

            channel.close();
            connection.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void consumer(boolean autoAck) {
        System.out.println("Start consumer [" + autoAck + "]");

        ConnectionFactory factory = new ConnectionFactory();

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, autoAck, consumer); // handshake required?

            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("Received: " + message);

            if(!autoAck) {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                System.out.print("Explicitly");
            }
            else {
                System.out.print("Automatically");
            }
            System.out.println(" acknowledged: " + message);

            channel.close();
            connection.close();
        }
        catch(IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Acknowledger ack = new Acknowledger();

        if(args.length == 0)
            ack.producer();
        else {
            ack.consumer(Integer.valueOf(args[0]) % 2 == 0);
        }
    }
}
