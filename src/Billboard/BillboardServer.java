package Billboard;
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

        if(o.equals("Client1")){
            System.out.println("Start new thread for CLIENT");
            Thread t = new ClientHandler(s, ois, oos);
            t.start();
            System.out.println("start CLIENT");
        }
        if(o.equals("TestPanel1")){
            System.out.println("Start new thread for PANEL 1 (VIEWER)");
            Thread panel1Thread = new ViewerHandler(s, ois, oos);
            panel1Thread.start();
            System.out.println("start PANEL1 (VIEWER)");
        }
        if(o.equals("ControlPanel")){
            System.out.println("Start new thread for ControlPanel (CONTROL PANEL)");
            Thread panel2Thread = new ControlPanelHandler(s, ois, oos);
            panel2Thread.start();
            System.out.println("start ControlPanel (CONTROL PANEL)");
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
        // FOR 2nd client (ControlPanel)
        Socket socket2 = null;
        try{
            socket1 = serverSocket.accept();
            threadHandler(socket1);

            socket2 = serverSocket.accept();
            threadHandler(socket2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
