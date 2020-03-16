package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    protected Socket socket;
    protected ServerSocket serverSocket;
    protected final int DEFAULT_PORT = 7777;
    protected InputStream in;
    protected OutputStream out;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server created!");
        socket = serverSocket.accept();
        System.out.println("Client connected!");
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(DEFAULT_PORT);
        System.out.println("Server created!");
        socket = serverSocket.accept();
        System.out.println("Client connected!");
        in = socket.getInputStream();
        out = socket.getOutputStream();
    }

    public void stop() throws IOException {
        socket.close();
        serverSocket.close();
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
