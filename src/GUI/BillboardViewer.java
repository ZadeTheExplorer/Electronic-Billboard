package GUI;
import javax.swing.*;
import javax.sql.*;
import java.io.*;
import java.net.Socket;

public class BillboardViewer extends JFrame implements Runnable{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;

    public BillboardViewer(String title){
        super(title);
    }

    public void Display(){
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        Display();
    }

    public static void main(String[] args) throws IOException{
        SwingUtilities.invokeLater(new BillboardViewer("Billboard Viewer"));
        Socket bbViewer = new Socket("host",1234);
        OutputStream billboard = bbViewer.getOutputStream();
        System.out.println("Connected to server");
        BufferedOutputStream content = new BufferedOutputStream(billboard);
        bbViewer.close();
    }
}
