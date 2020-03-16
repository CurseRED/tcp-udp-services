package model;

import java.io.IOException;

public class EchoProtocolServer extends TcpServer {

    private Response response;

    public void start() throws IOException {
        super.start();
        response = new Response();
        response.setParent(this);
        response.start();
    }

    public void stop() throws IOException {
        super.stop();
        response.setStopped();
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
                    out.write(buffer);
                    if (new String(buffer).trim().equalsIgnoreCase("!stop")) {
                        parent.stop();
                    }
                } catch (IOException e) {

                }
            }
        }
    }
}
