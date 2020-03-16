package model;

import java.io.IOException;

public class TimeProtocolServer extends TcpServer {

    private final int DEFAULT_PORT = 37;

    @Override
    public void start() throws IOException {
        super.start(DEFAULT_PORT);
    }

    public void sendTime() throws IOException {
        long currentTime = System.currentTimeMillis();
        currentTime /= 1000;
        String response = Long.toString(currentTime);
        out.write(response.getBytes());
    }
}
