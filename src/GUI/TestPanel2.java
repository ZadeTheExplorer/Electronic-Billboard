package GUI;

import Billboard.Billboard;
import Billboard.Request.DeleteBillboardRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class TestPanel2 extends JFrame implements ActionListener, Runnable{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;
    private JLabel testLabel2;
    private JTextField tfTest;
    private JButton testButton;
    static ObjectOutputStream oos = null;
    public TestPanel2(String title) throws SQLException {
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
        JPanel testPanel2 = new JPanel();
        testPanel2.setOpaque(true);
        testPanel2.setBorder(BorderFactory.createTitledBorder("TEST 2"));
        testPanel2.setLayout(new FlowLayout());
        testPanel2.setPreferredSize(new Dimension(500,300));
        System.out.println("Panel Created");
        testLabel2 = new JLabel();
        testLabel2.setVisible(false);
        System.out.println("Label Created");
        testButton = new JButton("Print input");
        testButton.addActionListener(this);
        testButton.setFocusPainted(false);
        System.out.println("Button Created");
        tfTest = new JTextField();
        tfTest.setPreferredSize(new Dimension(100,20));
        System.out.println("Input Created");
        addToPanel(testPanel2,testLabel2,constraints,1,1,1,1);
        addToPanel(testPanel2,tfTest,constraints,1,1,1,1);
        addToPanel(testPanel2,testButton,constraints,1,1,1,1);
        System.out.println("Add Panel, Button, Input to Panel");
        this.getContentPane().add(testPanel2,BorderLayout.CENTER);
        System.out.println("Panel get content");
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
        if(e.getSource() == testButton){
            testLabel2.setText(tfTest.getText());
            testLabel2.setVisible(true);
            try {
                //TODO: DELETE FROM BILLBOARD LIST
                Billboard billboard = new Billboard("COVID-19", 1, "white", "red",
                        "black", "https://d2v9ipibika81v.cloudfront.net/uploads/sites/40/COVID-19.jpg",
                        "Wash your hand", "Stay at home!");
                oos.writeObject(new DeleteBillboardRequest(billboard));
                oos.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
//            try {
//                oos.close();
//                System.exit(0);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }

        }
    }

    @Override
    public void run() {
        createGUI();
    }

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        TestPanel2 panel = new TestPanel2("Test Panel2");
        panel.run();

        Thread.sleep(5000);

        Socket socket = new Socket("localhost", 1234);
        // obtaining input and out streams

        oos = new ObjectOutputStream(socket.getOutputStream());

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        oos.writeObject("TestPanel2");
        oos.flush();
        System.out.println("Identified!");
        panel.repaint();

    }
}
