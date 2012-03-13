package rmq;

import com.rabbitmq.client.*;
import com.rabbitmq.tools.jsonrpc.JsonRpcClient;
import com.rabbitmq.tools.jsonrpc.JsonRpcServer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class JsonRPC {
    private static final int RPC_TIMEOUT_ONE_SECOND = 1000;
    private static final String QUEUE_NAME = "Hello";

    private class MyRpcServer extends JsonRpcServer {
        public MyRpcServer(Channel channel, String queueName, Class<?> clazz, Object instance) throws IOException {
            super(channel, queueName, clazz, instance);
        }

        @Override
        public void handleCast(byte[] requestBody)
        {
            if(requestBody.length == 0) {
                System.out.println("Empty message, terminating.");
                terminateMainloop();
            }
        }
    }

    public interface JsonSvc {
        String greeting(String name);
        int sum(List<Integer> values);
    }

    private class MyJsonSvc implements JsonSvc {

        @Override
        public String greeting(String name) {
            System.out.println("Processing greeting");
            return "Hello, " + name + ", from JSON-RPC over AMQP!";
        }

        @Override
        public int sum(List<Integer> values) {
            System.out.println("Processing sum");
            int s = 0;
            for (int i: values) { s += i; }
            return s;
        }
    }

    public void server() {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            JsonRpcServer server = new MyRpcServer(channel, QUEUE_NAME, JsonSvc.class, new MyJsonSvc());

            System.out.println("JSON-RPC server is up.");
            server.mainloop();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try{ if(connection != null) connection.close(); } catch(IOException e) {}
        }
    }

    public void client() {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            JsonRpcClient client = new JsonRpcClient(channel, "", QUEUE_NAME, RPC_TIMEOUT_ONE_SECOND);
            JsonSvc service = (JsonSvc)client.createProxy(JsonSvc.class);

            System.out.println(service.greeting("Rabbit"));

            List<Integer> numbers = new ArrayList<>();
            numbers.add(1);
            numbers.add(2);
            numbers.add(3);
            System.out.println("1 + 2 + 3 = " + service.sum(numbers));

            System.out.println("Sending a terminator");
            client.publish(null, null);
        } catch (TimeoutException te) {
            System.out.println("Can't connect to the server before timeout.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{ if(connection != null) connection.close(); } catch(IOException e) {}
        }
    }


    public static void main(String[] args) {
        JsonRPC rpc = new JsonRPC();
        if(args.length == 0)
            rpc.server();
        else
            rpc.client();

    }
}
