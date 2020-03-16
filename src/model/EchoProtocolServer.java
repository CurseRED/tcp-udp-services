package model;

import java.io.IOException;

public class EchoProtocolServer extends TcpServer {

    private Response response;

    public void start() throws IOException {
        super.start();
        response = new Response();
        response.start();
    }

    public void stop() throws IOException {
        super.stop();
        response.setStoped();
    }

    private class Response extends Thread {

        private boolean isStoped = false;

        public void setStoped() {
            isStoped = true;
        }

        @Override
        public void run() {
            while (!isStoped) {
                try {
                    byte[] buffer = new byte[1024];
                    in.read(buffer);
                    out.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
