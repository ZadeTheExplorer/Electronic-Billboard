package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JFrame implements ActionListener, Runnable {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private JPanel pnl1;
    private JButton btn1;

    public ControlPanel(String title) throws HeadlessException {
        super(title);
    }

    private void createGUI(){
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnl1 = createPanel(Color.BLACK);
        btn1 = createButton("add");
        pnl1.setLayout(new GridBagLayout());
        pnl1.add(btn1, BorderLayout.NORTH);
        this.getContentPane().add(pnl1,BorderLayout.WEST);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;




        pnl1.setPreferredSize(new Dimension(200, 40));


        addToPanel(pnl1, btn1,constraints,0,0,2,1);
//        addToPanel(pnl1, btnUnload,constraints,3,0,2,1);
//        addToPanel(pnl1, btnFind,constraints,0,2,2,1);
//        addToPanel(pnl1, btnSwitch,constraints,3,2,2,1);


    }


    private void addToPanel(JPanel jp,Component c, GridBagConstraints
            constraints,int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    private JPanel createPanel(Color c) {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(c);
        return jPanel;
    }

    private JButton createButton(String text){
        JButton jButton = new JButton();
        jButton.setText(text);
        jButton.addActionListener(this);
        return jButton;
    }


    @Override
    public void run() {
        createGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ControlPanel("BillboardControlPanel"));
    }
}
