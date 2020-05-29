package Billboard;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStream;
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
        try {
            select = connection.prepareCall("call userDisplay()");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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



    public static void main(String[] args) throws IOException, SQLException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("[SERVER] Waiting for client connection...");
        Database.init();
        for(;;){
            Socket socket = serverSocket.accept();
            System.out.println("[SERVER] Connected to client!");

            InputStream inputStream = socket.getInputStream();
            BufferedInputStream input = new BufferedInputStream(inputStream);
            input.read();

            socket.close();
        }


    }
}
