//Sumitram Kumar (201951156)

package Question1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte[] buf;

        String input = scanner.nextLine();
        buf = input.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, ip, 1234);
        datagramSocket.send(sendPacket);

        byte[] result = new byte[65535];
        DatagramPacket receivePacket = new DatagramPacket(result, result.length);
        datagramSocket = new DatagramSocket(1234);
        datagramSocket.receive(receivePacket);
        String singleString = data(result).toString();
        String[] output = singleString.split("\t");
        for (String string : output) {
            System.out.println(string);
        }
    }

    static StringBuilder data(byte[] bytes) {

        if (bytes == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (bytes[i] != 0) {
            result.append((char) bytes[i]);
            i++;
        }

        return result;
    }
}
