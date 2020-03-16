package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {

    protected Socket socket;
    private final int DEFAULT_PORT = 7777;
    protected InputStream in;
    protected OutputStream out;

    public void start() throws IOException {
        socket = new Socket("localhost", DEFAULT_PORT);
        System.out.println("Connected to server!");
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    public void start(int port) throws IOException {
        socket = new Socket("localhost", port);
        System.out.println("Connected to server!");
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    public void start(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        System.out.println("Connected to server!");
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public void sendData(byte[] data) throws IOException {
        out.write(data);
    }

    public byte[] getData() throws IOException {
        byte[] buffer = new byte[1024];
        in.read(buffer);
        return buffer;
    }
}
