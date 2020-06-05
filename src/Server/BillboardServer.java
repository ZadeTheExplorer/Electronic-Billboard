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
    private final Connection connection;
    private static Thread[] activeThreads;
    private final ServerSocket serverSocket;
    private static BillboardServer instance;
    /**
     * Instantiates a new Billboard server.
     */
    private BillboardServer() throws IOException {
        connection = DBConnection.getInstance();
        activeThreads = new Thread[3];
        serverSocket = new ServerSocket(1234);
    }

    public static BillboardServer getInstance() throws IOException {
        if(instance == null){
            instance = new BillboardServer();
        }

        return instance;
    }

//    public static Thread[] getActiveThreads() {
//        return activeThreads;
//    }
//    public static void addActiveThread(Thread thread){
//        for (int i=0; i<activeThreads.length; i++){
//            if(activeThreads[i] == null) {
//                activeThreads[i] = thread;
//                break;
//            }
//        }
//    }
//    public static void removeThread(Thread thread){
//        for (int i=0; i<activeThreads.length; i++){
//            if(activeThreads[i].equals(thread)) {
//                activeThreads[i] = null;
//                break;
//            }
//        }
//    }
//    public void hasSlotSocket() throws IOException, ClassNotFoundException {
//        for (int i=0; i<activeThreads.length; i++){
//            if(activeThreads[i] == null) {
//                threadHandler(serverSocket.accept());
//                return;
//            }
//        }
//    }
    /**
     * Close.
     */
    public void close() {
        try {
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

        if(o.equals("Viewer")){
            System.out.println("Start new thread for Viewer");
            Thread viewerThread = new ViewerHandler(s, ois, oos);
            //addActiveThread(viewerThread);
            viewerThread.start();

            System.out.println("[Server] Connection started to Viewer");
        }
        if(o.equals("ControlPanel")){
            System.out.println("Start new thread for ControlPanel");
            Thread ControlPanelThread = new ControlPanelHandler(s, ois, oos);
            //addActiveThread(ControlPanelThread);
            ControlPanelThread.start();
            System.out.println("[Server] Connection started to ControlPanel");
        }
        if(o.equals("Login")){
            System.out.println("Start new thread for Login");
            Thread LoginThread = new LoginHandler(s, ois, oos);
            //addActiveThread(LoginThread);
            LoginThread.start();
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

        BillboardServer server = BillboardServer.getInstance();

        System.out.println("[SERVER] Waiting for client connection...");

        //TODO: UNCOMMENT IF THIS IS FIRST RUN
        //Database.init();

        try {
//            while(true){
//                server.hasSlotSocket();
//            }
            Socket socket1 = server.serverSocket.accept();
            threadHandler(socket1);
            Socket socket2 = server.serverSocket.accept();
            threadHandler(socket2);
            Socket socket3 = server.serverSocket.accept();
            threadHandler(socket3);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
