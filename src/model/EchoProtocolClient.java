package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoProtocolClient {

    private Socket socket;
    private final int DEFAULT_PORT = 7777;
    private InputStream in;
    private OutputStream out;
    private Response response;

    public void start() throws IOException {
        socket = new Socket("localhost", DEFAULT_PORT);
        System.out.println("Connected to server!");
        in = socket.getInputStream();
        out = socket.getOutputStream();
        response = new Response();
        response.start();
    }

    public void stop() throws IOException {
        response.setStoped();
        socket.close();
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
