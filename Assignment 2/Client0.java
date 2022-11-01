//Sumitram Kumar (201951156)
//Assignment 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client0 {

    public static void main(String[] args) throws IOException {

        Socket s = new Socket("localhost", 4998);

        PrintWriter pr = new PrintWriter(s.getOutputStream());
        pr.println("0");
        pr.flush();

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader br = new BufferedReader(in);

        String str = br.readLine();
        String[] output = str.split("\t");
        for (String string : output) {
            System.out.println(string);
        }
    }
}
