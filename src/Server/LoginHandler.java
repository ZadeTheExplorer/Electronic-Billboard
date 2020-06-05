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

/**
 * The type Login handler.
 */
public class LoginHandler extends Thread {
    /**
     * The Ois.
     */
    final ObjectInputStream ois;
    /**
     * The Oos.
     */
    final ObjectOutputStream oos;
    /**
     * The S.
     */
    final Socket s;
    /**
     * The Fordate.
     */
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    /**
     * The Fortime.
     */
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");

    /**
     * Instantiates a new Login handler.
     *
     * @param s   the s
     * @param dis the dis
     * @param dos the dos
     */
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
        while(true){

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
                ServerRespond res = new ServerRespond(received, oos, ois);
                res.handle();

            } catch (NoSuchAlgorithmException | IOException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }


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