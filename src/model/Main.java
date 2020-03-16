package model;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Choose server or client (server - 1, client - other): ");
        int choose = in.nextInt();
        System.out.print("Choose protocol (Echo - 1, Time - 2, DayTime - 3, WhoIs - 4, Finger - 5, RLogin - 6, Telnet - 7): ");
        int protocol = in.nextInt();
        if (choose == 1) {
            switch (protocol) {
                case 1:
                    EchoProtocolServer server = new EchoProtocolServer();
                    server.start();
                    break;
                case 2:
                    TimeProtocolServer timeServer = new TimeProtocolServer();
                    timeServer.start();
                    timeServer.sendTime();
                    timeServer.stop();
                    break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7: break;
            }
        } else {
            switch (protocol) {
                case 1:
                    EchoProtocolClient echoClient = new EchoProtocolClient();
                    echoClient.start();
                    String msg;
                    while (true) {
                        msg = in.nextLine();
                        echoClient.sendMessage(msg);
                    }
                case 2:
                    TimeProtocolClient timeClient = new TimeProtocolClient();
                    timeClient.start();
                    System.out.println(timeClient.getTime());
                    timeClient.stop();
                    break;
                case 3: break;
                case 4: break;
                case 5: break;
                case 6: break;
                case 7: break;
            }
        }
    }
}
