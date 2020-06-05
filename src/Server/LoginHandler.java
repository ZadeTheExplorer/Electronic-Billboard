package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginHandler extends Thread {
    final ObjectInputStream ois;
    final ObjectOutputStream oos;
    final Socket s;
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");

    // Constructor
    public LoginHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos) {
        this.s = s;
        this.ois = dis;
        this.oos = dos;
    }

    @Override
    public void run() {
        Object received;
        Object toreturn;


        try {
            oos.writeObject("[Server] Connected");
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                // receive the answer from client
                received = ois.readObject();

                if (received.equals("Exit")) {
                    System.out.println("[Login] " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    //BillboardServer.removeThread(this);
                    System.out.println("Connection closed");
                    break;
                }
                ServerRespond res = new ServerRespond(received, oos);
                res.handle();

            } catch (NoSuchAlgorithmException | IOException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            // closing resources
            try {
                System.out.println("Closing stream IO at " + s);
                this.ois.close();
                this.oos.close();
                System.out.println("Closed Login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Login session End!");
        }
    }
}