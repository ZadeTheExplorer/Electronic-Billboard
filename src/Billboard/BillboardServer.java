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



    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("[SERVER] Waiting for client connection...");
        Database.init();

        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("Connected to " + socket.getInetAddress());
            System.out.println("[SERVER] Connected to client!");

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());


            Object o = objectInputStream.readObject();
            System.out.println("Received from client: " + o);
            objectOutputStream.close();
            socket.close();
        }

    }
}
