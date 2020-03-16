package model;

import java.io.IOException;

public class DayTimeProtocolClient extends TcpClient {

    private final int DEFAULT_PORT = 13;

    @Override
    public void start() throws IOException {
        super.start(DEFAULT_PORT);
    }

    public String getDayTime() throws IOException {
        byte[] buffer = new byte[128];
        in.read(buffer);
        return new String(buffer).trim();
    }
}
