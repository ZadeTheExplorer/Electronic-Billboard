package GUI;

import javax.swing.*;
import javax.swing.plaf.TableHeaderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TestPanel1 extends JFrame implements ActionListener, Runnable{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 150;
    public static JLabel testLabel;
    static DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    public TestPanel1(String title) throws SQLException {
        super(title);
    }

    private void createGUI(){
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GridBagConstraints constraints = new GridBagConstraints();
        //Defaults
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;
        JPanel testPanel = new JPanel();
        testPanel.setLayout(new FlowLayout());
        testPanel.setOpaque(true);
        testPanel.setBorder(BorderFactory.createTitledBorder("TEST 1"));
        testPanel.setPreferredSize(new Dimension(200,200));
        testLabel = new JLabel();
        testLabel.setVisible(true);
        addToPanel(testPanel,testLabel,constraints,1,1,1,1);
        //CHANGE THE TEXT LABEL, SWAP WITH AN OBJECT IF NEEDED
        testLabel.setText("Default text");
        this.getContentPane().add(testPanel,BorderLayout.CENTER);
    }

    private void addToPanel(JPanel jp, Component c, GridBagConstraints
            constraints, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {
        createGUI();
    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        TestPanel1 panel = new TestPanel1("Test Panel");

        panel.run();
        Socket socket = new Socket("localhost", 1234);
        // obtaining input and out streams

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        oos.writeObject("TestPanel1");
        oos.flush();
        System.out.println("Identified!");
        RequestTimer request = new RequestTimer(socket, oos);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(request, 0, 3000);
        System.out.println(ois.readObject().toString());
        try{
            while(true){
                // printing date or time as requested by client
                Object received = ois.readObject();
                System.out.println(received);
                testLabel.setText(received.toString() + " at: " + fortime.format(new Date()));

            }
        } finally {
            ois.close();
            oos.close();
        }


    }
}
