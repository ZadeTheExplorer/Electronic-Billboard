package Server;

import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler extends Thread {
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final ObjectInputStream ois;
    final ObjectOutputStream oos;
    final Socket s;


    // Constructor
    public ClientHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos)
    {
        this.s = s;
        this.ois = dis;
        this.oos = dos;
    }

    @Override
    public void run()
    {
        Object received;
        Object toreturn;
        outer:
        while (true)
        {
            try {
                // Ask user what he wants
                oos.writeObject("[S] What do you want? Request: ");
                oos.flush();
                // receive the answer from client
                received = ois.readObject();

                if(received.equals("Exit"))
                {
                    System.out.println("[C] " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");

                    System.out.println("Connection closed");
                    break outer;
                }

                // creating Date object
                Date date = new Date();

                // write on output stream based on the
                // answer from the client
                switch (received.toString()) {

                    case "Date" :
                        toreturn = "[S]" + fordate.format(date);
                        oos.writeObject(toreturn);
                        oos.flush();
                        break;

                    case "Time" :
                        toreturn = fortime.format(date);
                        oos.writeObject(toreturn);
                        oos.flush();
                        break;

                    default:
                        oos.writeObject("[S] Invalid input");
                        oos.flush();
                        break;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        try
        {
            s.close();
            // closing resources
            this.ois.close();
            this.oos.close();
            System.out.println("Closed IO.");
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Client Handler end.");
    }
}
