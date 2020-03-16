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
                case 3:
                    DayTimeProtocolServer dayTimeServer = new DayTimeProtocolServer();
                    dayTimeServer.start();
                    dayTimeServer.sendDayTime();
                    dayTimeServer.stop();
                    break;
                case 4:
                    System.out.println("WhoIs service is only for clients!");
                    break;
                case 5:
                    System.out.println("Finger service is only for clients!");
                    break;
                case 6: break;
                case 7: break;
            }
        } else {
            switch (protocol) {
                case 1:
                    EchoProtocolClient echoClient = new EchoProtocolClient();
                    echoClient.start();
                    String msg = "";
                    while (!msg.equalsIgnoreCase("!stop")) {
                        msg = in.next();
                        echoClient.sendMessage(msg);
                    }
                    break;
                case 2:
                    TimeProtocolClient timeClient = new TimeProtocolClient();
                    timeClient.start();
                    System.out.println(timeClient.getTime());
                    timeClient.stop();
                    break;
                case 3:
                    DayTimeProtocolClient dayTimeClient = new DayTimeProtocolClient();
                    dayTimeClient.start();
                    System.out.println(dayTimeClient.getDayTime());
                    dayTimeClient.stop();
                    break;
                case 4:
                    WhoIsProtocolClient whoIsClient = new WhoIsProtocolClient();
                    whoIsClient.start();
                    String whoIsQuery = in.next();
                    System.out.println(whoIsClient.getWhoIs(whoIsQuery));
                    whoIsClient.stop();
                    break;
                case 5:
                    FingerProtocolClient fingerClient = new FingerProtocolClient();
                    String fingerQuery = in.next();
                    fingerClient.start(fingerQuery);
                    fingerQuery = in.next();
                    System.out.println(fingerClient.getFingerResponse(fingerQuery));
                    break;
                case 6:
                    RLoginProtocolClient rLoginClient = new RLoginProtocolClient();
                    String name1 = in.next();
                    String name2 = in.next();
                    break;
                case 7: break;
            }
        }
    }
}
