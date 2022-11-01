//Sumitram Kumar (201951156)
//Assignment 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {

    public static final String path_bash = "C:/Program Files/Git/bin/bash.exe";


    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(4998);
        Socket s = ss.accept();

        System.out.println("Connection with client");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader br = new BufferedReader(in);
        PrintWriter pr = new PrintWriter(s.getOutputStream());

        String str = br.readLine();
        StringBuilder result = new StringBuilder();

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (Objects.equals(str, "0")) {
            processBuilder.command(path_bash, "-c", "ls");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null){
                result.append(line).append("\t");
            }
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                pr.println(result);
            }else {
                pr.println("Error");
            }
            pr.flush();
        } else {
            //date command
            processBuilder.command(path_bash, "-c", "date");
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null){
                result.append(line + "\n");
            }
            int exitVal = process.waitFor();
            if (exitVal == 0) {
                pr.println(result);
            }else {
                pr.println("Error");
            }
            pr.flush();
        }
    }
}
