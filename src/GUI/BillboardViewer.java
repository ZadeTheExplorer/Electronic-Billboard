package GUI;
import javax.swing.*;
import javax.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;

public class BillboardViewer extends JFrame implements Runnable, ActionListener {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private JPanel billboard;
    private JTextArea titleBox;
    private JTextArea descriptionBox;
    private String title;
    private String description;
    private JLabel billboardImage;
    static String currentDir = System.getProperty("user.dir");
    public BillboardViewer(String title){
        super(title);
    }

    public void Display(){
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        title = "This is a billboard advertisement";
        description = "This billboard is advertising about the food commercial! Hamburger is yummy";
        titleBox = createTextBox(title, Color.WHITE, WIDTH/30, WIDTH*2/3);
        billboardImage = new JLabel();
        billboardImage.setIcon(new ImageIcon(new ImageIcon(currentDir+"\\src\\resources\\bitcoin.jpg").getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT)));
        descriptionBox = createTextBox(description, Color.GREEN, WIDTH/44, WIDTH*2/3);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 0;
        constraints.weighty = 2;

        billboard = new JPanel(new GridBagLayout());
        billboard.setBackground(Color.BLACK);

        addToPanel(billboard, titleBox,constraints,0,1,1,1);
        addToPanel(billboard, billboardImage,constraints,0,2,1,1);
        descriptionBox.setAlignmentX(CENTER_ALIGNMENT);
        addToPanel(billboard, descriptionBox,constraints,0,3,1,1);

        this.getContentPane().add(billboard, BorderLayout.CENTER);

    }

    public JTextArea createTextBox(String textBoxContent, Color textBoxColor, int textSize, int textBoxWidth) {
        JTextArea textBox = new JTextArea(textBoxContent);
        textBox.setFont(new Font("Arial",Font.BOLD,textSize));
        textBox.setForeground(textBoxColor);
        textBox.setOpaque(false);
        textBox.setEditable(false);
        textBox.setLineWrap(false);
        textBox.setSize(textBoxWidth,HEIGHT);
        return textBox;
    }

    private void addToPanel(JPanel jp, Component c, GridBagConstraints constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    @Override
    public void run() {
        Display();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SwingUtilities.invokeLater(new BillboardViewer("Billboard Viewer"));
        Socket BillboardViewerSocket = new Socket("localhost", 1234);
        // obtaining input and out streams

        ObjectOutputStream oos = new ObjectOutputStream(BillboardViewerSocket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(BillboardViewerSocket.getInputStream());

        oos.writeObject("Billboard Viewer");
        oos.flush();
        System.out.println("Identified!");
        RequestTimer request = new RequestTimer(BillboardViewerSocket, oos);
        java.util.Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(request, 0, 3000);
        System.out.println(ois.readObject().toString());
        try{
            while(true){
                // printing date or time as requested by client
                Object received = ois.readObject();
                System.out.println(received);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ois.close();
            oos.close();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
