package Billboard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ViewerHandler extends Thread{
        DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat fortime = new SimpleDateFormat("hh:mm:ss");

        ObjectInputStream ois;
        ObjectOutputStream oos;
        Socket s;


        // Constructor
        public ViewerHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos)
        {
            this.s = s;
            this.ois = dis;
            this.oos = dos;
        }

        @Override
        public void run() {
            Object received;
            Object toReturn;

            // Ask user what he wants
            try {
                oos.writeObject("[S] Hello, start connecting... ");oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    // receive the answer from client
                    received = ois.readObject();

                    if (received.equals("Exit")) {
                        System.out.println("[C] " + this.s + " sends exit...");
                        System.out.println("Closing this connection.");
                        this.s.close();
                        System.out.println("Connection closed");
                        break;
                    }

                    // write on output stream based on the
                    // answer from the client
                    if (received.equals("Current")) {
                        oos.writeObject("[S] Request received!");
                        oos.flush();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            // closing resources
            try {
                System.out.println("Closing stream IO at " + s);
                this.ois.close();this.oos.close();
                System.out.println("Closed Viewer");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Viewer End");
        }
}
