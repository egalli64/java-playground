package rmq;

import com.rabbitmq.client.*;
import java.io.IOException;

public class WorkQueue {
    private static final String QUEUE_NAME = "hello";

    public void producer() {
        System.out.println("Starting producer");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try
        {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 10; ++i) {
                sb.append("X");

                String message = sb.toString();
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

                System.out.println(message);
            }

            channel.close();
            connection.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void doWork(byte[] task) throws InterruptedException {
        System.out.print(task.length + ": ");
        for(int i = 0; i < task.length; ++i) {
            Thread.sleep(500);
            System.out.print('.');
        }
        System.out.println();
    }
    
    public void receiver() {
        System.out.println("Starting receiver");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, false, consumer);

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                doWork(delivery.getBody());

                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        }
        catch(IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] argv) {
        WorkQueue wq = new WorkQueue();

        if(argv.length == 0)
            wq.producer();
        else
            wq.receiver();
    }
}
