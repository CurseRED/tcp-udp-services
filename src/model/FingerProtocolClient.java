package model;

import java.io.IOException;

public class FingerProtocolClient extends TcpClient {

    private final int DEFAULT_PORT = 79;

    public void start(String domain) throws IOException {
        super.start(domain, DEFAULT_PORT);
    }

    public String getFingerResponse(String msg) throws IOException {
        msg = msg + "\n";
        out.write(msg.getBytes());
        byte[] buffer = new byte[512];
        in.read(buffer);
        return new String(buffer);
    }
}
