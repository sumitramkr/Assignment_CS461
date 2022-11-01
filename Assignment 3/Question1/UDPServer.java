//Sumitram Kumar (201951156)

package Question1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    public static final String path_bash = "C:/Program Files/Git/bin/bash.exe";

    public static void main(String[] args) throws Exception {

        DatagramSocket datagramSocket = new DatagramSocket(1234);
        InetAddress ip = InetAddress.getLocalHost();
        byte[] receive = new byte[65535];
        DatagramPacket receivedPacket = new DatagramPacket(receive, receive.length);
        datagramSocket.receive(receivedPacket);
        String command = data(receive).toString();
        StringBuilder result = new StringBuilder();
        byte[] send = null;
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (command.equals("0")) {
            processBuilder.command(path_bash, "-c", "ls");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\t");
            }
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                send = result.toString().getBytes();
                DatagramPacket sendPacket = new DatagramPacket(send, send.length, ip, 1234);
                datagramSocket.send(sendPacket);
            } else {
                System.out.println("There is some error running that command");
            }
        } else {
            processBuilder.command(path_bash, "-c", "date");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                send = result.toString().getBytes();
                DatagramPacket sendPacket = new DatagramPacket(send, send.length, ip, 1234);
                datagramSocket.send(sendPacket);
            } else {
                System.out.println("There is some error running that command");
            }
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
