package Billboard;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BillboardServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("[SERVER] Waiting for client connection...");

        for(;;){
            Socket socket = serverSocket.accept();
            System.out.println("[SERVER] Connected to client!");

            InputStream inputStream = socket.getInputStream();
            BufferedInputStream input = new BufferedInputStream(inputStream);
            input.read();
        //..
            socket.close();
    }
    }
}
