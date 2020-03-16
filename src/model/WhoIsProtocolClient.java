package model;

import java.io.IOException;

public class WhoIsProtocolClient extends TcpClient {

    private final String DEFAULT_IP = "whois.tcinet.ru";
    private final int DEFAULT_PORT = 43;

    @Override
    public void start() throws IOException {
        super.start(DEFAULT_IP, DEFAULT_PORT);
    }

    public String getWhoIs(String msg) throws IOException {
        out.write(msg.getBytes());
        byte[] buffer = new byte[512];
        in.read(buffer);
        return new String(buffer);
    }
}
