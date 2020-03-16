package model;

import java.io.IOException;

public class RLoginProtocolServer extends TcpServer {

    private boolean isWorking = true;
    private final String DEFAULT_PASSWORD = "12341234";
    private Response response;

    @Override
    public void start() throws IOException {
        super.start();
    }

    @Override
    public void stop() throws IOException {
        super.stop();
        response.setStopped();
        isWorking = false;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void initializeConnection() throws IOException {
        byte[] buffer = new byte[256];
        in.read(buffer);
        System.out.println(new String(buffer));
        out.write("password".getBytes());
        in.read(buffer);
        if (new String(buffer).trim().equals(DEFAULT_PASSWORD)) {
            sendMessage("All good!");
        }
        response = new Response();
        response.setParent(this);
        response.start();
    }

    public void sendMessage(String msg) throws IOException {
        out.write(msg.getBytes());
    }

    private class Response extends Thread {

        private boolean isStopped = false;
        private TcpServer parent;

        public void setStopped() {
            isStopped = true;
        }

        public void setParent(TcpServer tcpServer) {
            parent = tcpServer;
        }

        @Override
        public void run() {
            while (!isStopped) {
                try {
                    byte[] buffer = new byte[1024];
                    in.read(buffer);
                    if (new String(buffer).trim().equalsIgnoreCase("!stop")) {
                        parent.stop();
                    }
                    System.out.println("Client response: " + new String(buffer));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
