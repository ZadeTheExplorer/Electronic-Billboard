package GUI;
import ElectronicBillboardObject.Billboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Timer;

public class BillboardViewer extends JFrame implements Runnable, ActionListener {
    private Socket socket;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream objectInputStream;


    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private Color backgroundColor;
    private Color titleColor;
    private Color descriptionColor;
    private JPanel billboardPanel;
    private JTextArea titleBox;
    private JTextArea descriptionBox;
    private String title;
    private String description;
    private JLabel billboardImage;
    private Billboard billboard;
    static String currentDir = System.getProperty("user.dir");
    public BillboardViewer(String title) throws IOException {
        super(title);
        socket = new Socket("localhost", 1234);
        // obtaining input and out streams
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void Display(){
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        backgroundColor = Color.LIGHT_GRAY;
        descriptionColor = Color.BLACK;
        titleColor = Color.WHITE;
        title = "This is a billboard advertisement";
        description = "This billboard is advertising about the food commercial! Hamburger is yummy";
        titleBox = createTextBox(title, titleColor, WIDTH/30, WIDTH*2/3);
        billboardImage = new JLabel();
        billboardImage.setIcon(new ImageIcon(new ImageIcon(currentDir+"\\src\\resources\\bitcoin.jpg").getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT)));
        descriptionBox = createTextBox(description, descriptionColor, WIDTH/44, WIDTH*2/3);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 0;
        constraints.weighty = 2;

        billboardPanel = new JPanel(new GridBagLayout());
        billboardPanel.setBackground(backgroundColor);

        addToPanel(billboardPanel, titleBox,constraints,0,1,1,1);
        addToPanel(billboardPanel, billboardImage,constraints,0,2,1,1);
        descriptionBox.setAlignmentX(CENTER_ALIGNMENT);
        addToPanel(billboardPanel, descriptionBox,constraints,0,3,1,1);

        this.getContentPane().add(billboardPanel, BorderLayout.CENTER);
        billboardPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                System.exit(0);
            }
        });
        // Add action for onClose of Viewer
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int i=JOptionPane.showConfirmDialog(null, "Do you want to close Billboard Viewer?");
                if(i==0) {
                    try {
                        objectOutputStream.close();
                        objectInputStream.close();
                        socket.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    System.exit(0);//Exit
                }
            }
        });
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
    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
    }
    @Override
    public void run() {
        Display();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BillboardViewer viewer = new BillboardViewer("Billboard Viewer");


        objectOutputStream.writeObject("Viewer");
        objectOutputStream.flush();

        System.out.println("[Viewer] Connect successfully to Server.\n Start sending request to current billboard!");
        RequestTimer request = new RequestTimer(objectOutputStream, objectInputStream, viewer);

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(request, 0, 15000);
        System.out.println(objectInputStream.readObject().toString());

        SwingUtilities.invokeLater(viewer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
