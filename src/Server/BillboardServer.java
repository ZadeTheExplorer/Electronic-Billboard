package Server;

import Database.DBConnection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

/**
 * The type Billboard server.
 */
public class BillboardServer {
    private Connection connection;
    private CallableStatement deletePerson;
    private CallableStatement insert;
    private CallableStatement select;

    /**
     * Instantiates a new Billboard server.
     */
    public BillboardServer() {
        connection = DBConnection.getInstance();
    }

    /**
     * Close.
     */
    public void close() {
        try {
            //TODO: Close all SQL command
            select.close();
            insert.close();
            deletePerson.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Thread handler.
     *
     * @param s the s
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static void threadHandler(Socket s) throws IOException, ClassNotFoundException {
        System.out.println("Connected to " + s.getInetAddress() + ", at: " + s.getPort());

        ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        Object o = ois.readObject();

//        if(o.equals("Client1")){
//            System.out.println("Start new thread for CLIENT");
//            Thread t = new ClientHandler(s, ois, oos);
//            t.start();
//            System.out.println("start CLIENT");
//        }
        if(o.equals("Viewer")){
            System.out.println("Start new thread for Viewer");
            Thread panel1Thread = new ViewerHandler(s, ois, oos);
            panel1Thread.start();
            System.out.println("[Server] Connection started to Viewer");
        }
        if(o.equals("ControlPanel")){
            System.out.println("Start new thread for ControlPanel");
            Thread panel2Thread = new ControlPanelHandler(s, ois, oos);
            panel2Thread.start();
            System.out.println("[Server] Connection started to ControlPanel");
        }
        if(o.equals("Login")){
            System.out.println("Start new thread for Login");
            Thread panel2Thread = new ControlPanelHandler(s, ois, oos);
            panel2Thread.start();
            System.out.println("[Server] User need to login for further actions");
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException            the io exception
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     * @throws InterruptedException   the interrupted exception
     */
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("[SERVER] Waiting for client connection...");

        //TODO: UNCOMMENT IF THIS IS FIRST RUN
        //Database.init();

        // FOR 1st client (Viewer)
        Socket socket1 = null;
        // FOR 2nd client (Login)
        Socket socket2 = null;
        // FOR 3rd client (ControlPanel)
        Socket socket3 = null;
        try{
            socket1 = serverSocket.accept();
            threadHandler(socket1);

            socket2 = serverSocket.accept();
            threadHandler(socket2);

            socket3 = serverSocket.accept();
            threadHandler(socket3);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
