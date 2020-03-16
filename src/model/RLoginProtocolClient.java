package model;

import java.io.IOException;

public class RLoginProtocolClient extends TcpClient {

    @Override
    public void start() throws IOException {
        super.start();
    }

    public void initializeConnection(String name1, String name2, String terminalType, int speed) throws IOException {
        String msg = "\0" + name1 + "\0" + name2 + "\0" + terminalType + "/" + speed + "\0";
        out.write(msg.getBytes());
    }
}
