package Socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client_1 {

    public static void main(String[] args) {
        Scanner Keyboard = new Scanner(System.in);
        try {
            // create a socket to a local host with port # 6789
            Socket s = new Socket("172.22.160.1", 6789);
            
            System.out.println("A connection is established and I'll now send a message");

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream());

            //Thread.sleep(5000);

            String str = (String) din.readUTF();
            System.out.println("Message received from server: " + str);

            String word1 = Keyboard.nextLine();
            dout.writeUTF(word1);
            dout.flush();

            String str2 = (String) din.readUTF();
            System.out.println("Message received from server: " + str2);

            String word2 = Keyboard.nextLine();
            dout.writeUTF(word2);
            dout.flush();

            String answer = (String) din.readUTF();
            System.out.println("Message received from server: " + answer);

            dout.close();
            s.close();

        } catch (IOException e) { System.out.println(e); }
    }
}
