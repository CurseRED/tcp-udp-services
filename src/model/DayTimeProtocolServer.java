package model;

import java.io.IOException;
import java.util.Date;

public class DayTimeProtocolServer extends TcpServer {

    private final int DEFAULT_PORT = 13;

    @Override
    public void start() throws IOException {
        super.start(DEFAULT_PORT);
    }

    public void sendDayTime() throws IOException {
        Date date = new Date();
        out.write(date.toString().getBytes());
    }
}
