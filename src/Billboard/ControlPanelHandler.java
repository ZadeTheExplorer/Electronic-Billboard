package Billboard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ControlPanelHandler extends Thread {
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Socket s;

    // Constructor
    public ControlPanelHandler(Socket s, ObjectInputStream ois, ObjectOutputStream oos)
    {
        this.s = s;
        this.ois = ois;
        this.oos = oos;
    }

    @Override
    public void run() {
        Object received;
        Object toReturn;

        // Ask user what he wants
        try {
            oos.writeObject("[S] Hello, start connecting to CONTROL PANEL");
            oos.flush();
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
                // Basicly, we have implemented the basic transfer data with String as code commented below
                // What we want is to make Server and TestPanel2 working with Object (In this demo situation is with Billboard, request: Add)
                // Our idea is create a ServerRespond class for Handle all the Respond when Server receives a Request from 1 of its clients
                // ControlPanelHandler is for handling all Request from ControlPanel (same with Viewer)
                // ServerRespond will deal with database (It uses stored Mysql procedure to execute the query)
                // TestPanel2 (Atom version of ControlPanel) will send a Request to Server when user click to its button
                // Current obstacles are implementing the code in ServerRespond and a better strategy to transfer data within the stream.
                ServerRespond res = new ServerRespond(received, oos);
                res.handle();
//                if (received.equals("Create Billboard")) {
//                    oos.writeObject("[S] Created a billboard!");
//                    oos.flush();
//                }
//                if (received.equals("Edit Billboard")) {
//                    oos.writeObject("[S] Edited a billboard");
//                    oos.flush();
//                }
            } catch (IOException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        // closing resources
        try {
            System.out.println("Closing stream IO at " + s);
            this.ois.close();this.oos.close();
            System.out.println("Closed ControlPanel");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Viewer End");
    }
}
