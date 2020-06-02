package Billboard;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class BillboardServer {
    private Connection connection;
    private CallableStatement deletePerson;
    private CallableStatement insert;
    private CallableStatement select;

    public BillboardServer() {
        connection = DBConnection.getInstance();
    }

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



    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("[SERVER] Waiting for client connection...");
        Database.init();

        while(true){
            Socket socket1 = null;
            Socket socket2 = null;
            try{
                socket1 = serverSocket.accept();
                System.out.println("Connected to " + socket1.getInetAddress() + ", at: " + socket1.getPort());

                ObjectInputStream objectInputStream = new ObjectInputStream(socket1.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());

                System.out.println("Start new thread for this client");

                Thread t = new ClientHandler(socket1, objectInputStream, objectOutputStream);
                t.start();
                System.out.println("start Client");

                socket2 = serverSocket.accept();
                System.out.println("Connected to " + socket2.getInetAddress() + ", at: " + socket2.getPort());

                ObjectInputStream ois = new ObjectInputStream(socket2.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket2.getOutputStream());
                Thread panel1Thread = new ViewerHandler(socket2, ois, oos);
                System.out.println("Start new thread for panel");

                panel1Thread.start();
                System.out.println("Start Panel");
                t.join();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
