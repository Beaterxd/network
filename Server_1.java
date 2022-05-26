package Socket;

import java.io.*;
import java.net.*;

public class Server_1 {
    public static int check(String w1, String w2, int size){
        char word1[] = w1.toCharArray();
        char word2[] = w2.toCharArray();
        char c;
        int i,j,flag=0;

        for( i = 0; i < size ; i++){
            word1[i] = Character.toUpperCase(word1[i]);
        }

        for( i = 0; i < size ; i++){
            word2[i] = Character.toUpperCase(word2[i]);
        }

        for ( i = 0; i < size ; i++) {
            for (j = i+1; j < size ; j++ ) 
            {
                if(word1[i] > word1[j]){
                    c = word1[i];
                    word1[i] = word1[j];
                    word1[j] = c;
                }
                if(word2[i] > word2[j]){
                    c = word2[i];
                    word2[i] = word2[j];
                    word2[j] = c;
                }
            }
        }

        for(i=0 ; i < size ; i++){
            if(word1[i] != word2[i]){
                flag = 1;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        try {

            // create a socket at port # 6789
            ServerSocket ss = new ServerSocket(6789);  // create a socket
            System.out.println("A socket is created and now waiting for connection.");

            // establish and wait for an incoming connection
            Socket s = ss.accept();
            System.out.println("A client has made a connection in.");

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            // wait for input message and display it
            String str1 = "Please enter first word: ";
            dout.writeUTF(str1);

            String word1 = (String) din.readUTF();
            System.out.println(word1);

            String str2 = "Please enter second word: ";
            dout.writeUTF(str2);

            String word2 = (String) din.readUTF();
            System.out.println(word2);

            int length = word1.length();

            if(word1.length() != word2.length()){
                String answer = word1 + " and " + word2 + " are not diagram.";
                dout.writeUTF(answer);
            }else{
                int ans = check(word1,word2,length);
                if(ans == 0 ){
                    String answer = word1 + " and " + word2 + " are diagram.";
                    dout.writeUTF(answer);
                }else if(ans == 1){
                    String answer = word1 + " and " + word2 + " are not diagram.";
                    dout.writeUTF(answer);
                }
            }
            ss.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
