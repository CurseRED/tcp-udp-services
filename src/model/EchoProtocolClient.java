package model;

import java.io.IOException;

public class EchoProtocolClient extends TcpClient {

    private Response response;

    @Override
    public void start() throws IOException {
        super.start();
        response = new Response();
        response.setParent(this);
        response.start();
    }

    @Override
    public void stop() throws IOException {
        super.stop();
        response.setStopped();
    }

    public void sendMessage(String msg) throws IOException {
        out.write(msg.getBytes());
    }

    private class Response extends Thread {

        private boolean isStopped = false;
        private TcpClient parent;

        public void setStopped() {
            isStopped = true;
        }

        public void setParent(TcpClient tcpClient) {
            parent = tcpClient;
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
                    System.out.println("Server response: " + new String(buffer));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
