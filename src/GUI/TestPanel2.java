package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TestPanel2 extends JFrame implements ActionListener, Runnable{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;
    private JLabel testLabel2;
    private JTextField tfTest;
    private JButton testButton;
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
        testLabel2 = new JLabel();
        testLabel2.setVisible(false);
        testButton = new JButton("Print input");
        testButton.addActionListener(this);
        testButton.setFocusPainted(false);
        tfTest = new JTextField();
        tfTest.setPreferredSize(new Dimension(100,20));
        addToPanel(testPanel2,testLabel2,constraints,1,1,1,1);
        addToPanel(testPanel2,tfTest,constraints,1,1,1,1);
        addToPanel(testPanel2,testButton,constraints,1,1,1,1);

        this.getContentPane().add(testPanel2,BorderLayout.CENTER);
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
        }
    }

    @Override
    public void run() {
        createGUI();
    }

    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(new TestPanel2("BillboardControlPanel"));
    }
}
