package rmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;

import java.io.IOException;
import java.util.UUID;

public class FibonacciRPC {

    private static final String RPC_QUEUE_NAME = "rpc_queue";
    private static final int MIN_INPUT = 0;
    private static final int MAX_INPUT = 46;

    private boolean checkInput(String arg) {
        if(arg.equalsIgnoreCase("x"))
            return true;

        int input;
        try {
            input = Integer.valueOf(arg);
        } catch(Exception e) {
            System.out.println("Input value should be a plain integer");
            return false;
        }

        if(input < MIN_INPUT || input > MAX_INPUT) {
            System.out.println("Input value should be in [" + MIN_INPUT + ".." + MAX_INPUT + ']');
            return false;
        }
        return true;
    }

    public void client(String arg) {
        if(checkInput(arg) == false)
            return;

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            if(arg.equalsIgnoreCase("x")) {
                System.out.println("Terminating Fibonacci server");
                channel.basicPublish("", RPC_QUEUE_NAME, null, null);
            }
            else {
                String queueName = channel.queueDeclare().getQueue();
                QueueingConsumer consumer = new QueueingConsumer(channel);
                channel.basicConsume(queueName, true, consumer); // implicit ack

                String id = UUID.randomUUID().toString();
                BasicProperties props = new BasicProperties.Builder().correlationId(id).replyTo(queueName).build();
                channel.basicPublish("", RPC_QUEUE_NAME, props, arg.getBytes());
                System.out.println("Reply to " + queueName + ", " + id);

                System.out.print("fibonacci(" + arg + ") = ");
                while (true) {
                    QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                    if (delivery.getProperties().getCorrelationId().equals(id)) {
                        System.out.println(new String(delivery.getBody()));
                        break;
                    }
                }
            }
        }
        catch (IOException |InterruptedException e) { e.printStackTrace(); }
        finally { try { connection.close(); } catch(IOException e) {} }
    }

    private int fibonacci(int n) {
        if (n ==0)
            return 0;
        if (n == 1)
            return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public void server() {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
            channel.basicQos(1);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(RPC_QUEUE_NAME, false, consumer); // explicit ack

            System.out.println("RPC Fibonacci calculator is ready");

            boolean terminator = false;
            while (true) {
                String response = "";

                QueueingConsumer.Delivery delivery = consumer.nextDelivery();

                try {
                    String message = new String(delivery.getBody());
                    if(message.isEmpty()) {
                        System.out.println("Empty message, terminating");
                        terminator = true;
                        break;
                    }

                    int n = Integer.parseInt(message);
                    System.out.println("Calculating fibonacci(" + message + ")");
                    if(n < MIN_INPUT || n > MAX_INPUT)
                        response = "N/A";
                    else
                        response += fibonacci(n);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    if(!terminator) {
                        BasicProperties bp = delivery.getProperties();
                        String corrId = bp.getCorrelationId();
                        String replyQueue = bp.getReplyTo();
                        System.out.println("Replying to " + replyQueue + ", " + corrId);
                        
                        BasicProperties replyProps = new BasicProperties.Builder().correlationId(corrId).build();
                        channel.basicPublish("", replyQueue, replyProps, response.getBytes());
                    }
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                }
            }
        }
        catch  (Exception e) {
            e.printStackTrace();
        }
        finally {
            try{ if(connection != null) connection.close(); } catch(IOException e) {}
        }
    }

    public static void main(String[] args) {
        FibonacciRPC fib = new FibonacciRPC();

        if(args.length == 0)
            fib.server();
        else
            fib.client(args[0]);
    }
}
