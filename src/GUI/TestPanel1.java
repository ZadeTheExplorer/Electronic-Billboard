package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TestPanel1 extends JFrame implements ActionListener, Runnable{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 150;
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
        JLabel testLabel = new JLabel();
        testLabel.setVisible(true);
        addToPanel(testPanel,testLabel,constraints,1,1,1,1);
        //CHANGE THE TEXT LABEL, SWAP WITH AN OBJECT IF NEEDED
        testLabel.setText("Testing 1");
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

    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(new TestPanel1("BillboardControlPanel"));
    }
}
