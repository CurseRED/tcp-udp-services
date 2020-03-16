package model;

import java.io.IOException;

public class EchoProtocolClient extends TcpClient{

    private Response response;

    @Override
    public void start() throws IOException {
        super.start();
        response = new Response();
        response.start();
    }

    @Override
    public void stop() throws IOException {
        super.stop();
        response.setStoped();
    }

    public void sendMessage(String msg) throws IOException {
        out.write(msg.getBytes());
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
                    System.out.println("Server response: " + new String(buffer));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
