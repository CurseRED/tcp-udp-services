package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoProtocolServer {

    private Socket socket;
    private ServerSocket serverSocket;
    private final int DEFAULT_PORT = 7777;
    private InputStream in;
    private OutputStream out;
    private Response response;

    public void start() throws IOException {
        serverSocket = new ServerSocket(DEFAULT_PORT);
        System.out.println("Server created!");
        socket = serverSocket.accept();
        System.out.println("Client connected!");
        in = socket.getInputStream();
        out = socket.getOutputStream();
        response = new Response();
        response.start();
    }

    public void stop() throws IOException {
        response.setStoped();
        socket.close();
        serverSocket.close();
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
