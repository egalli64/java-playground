package rmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class StringRPC {
    private static final String QUEUE_NAME = "rpc_hello";

    private class MyRpcServer extends StringRpcServer {
        public MyRpcServer(Channel channel, String queueName) throws IOException {
            super(channel, queueName);
        }

        @Override
        public String handleStringCall(String request) {
            System.out.println("Input: " + request);
            return "Hello, " + request + "!";
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

    public void server() {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            StringRpcServer server = new MyRpcServer(channel, QUEUE_NAME);
            System.out.println("RPC server is up");
            server.mainloop();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if(connection != null) connection.close(); } catch (IOException e) {}
        }
    }

    public void client(String arg) {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel channel = connection.createChannel();
            RpcClient service = new RpcClient(channel, "", QUEUE_NAME);

            if(arg.equalsIgnoreCase("x")) {
                System.out.println("Terminating server");
                service.publish(null, null); // no props required, empty message
            }
            else {
                String result =service.stringCall(arg);
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(connection != null) connection.close(); } catch (IOException e) {}
        }
    }

    public static void main(String[] args) {
        StringRPC fib = new StringRPC();

        if(args.length == 0)
            fib.server();
        else
            fib.client(args[0]);
    }
}
