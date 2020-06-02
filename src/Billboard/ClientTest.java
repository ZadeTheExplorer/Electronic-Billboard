package Billboard;

import GUI.TestPanel1;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {
    public static void main(String[] args) {
        try
        {
            Scanner scn = new Scanner(System.in);

            // getting localhost ip
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket s = new Socket(ip, 1234);

            // obtaining input and out streams

            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            // the following loop performs the exchange of
            // information between client and client handler
            while (true)
            {
                System.out.println(ois.readObject().toString());
                String tosend = scn.nextLine();
                oos.writeObject(tosend);
                oos.flush();
                // If client sends exit,close this connection
                // and then break from the while loop
                if(tosend.equals("Exit"))
                {
                    System.out.println("Closing this connection : " + s);
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                // printing date or time as requested by client
                Object received = ois.readObject();
                System.out.println(received.toString());
            }

            // closing resources
            scn.close();
            ois.close();
            oos.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
