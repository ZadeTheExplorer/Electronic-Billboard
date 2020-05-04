package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Login extends JFrame implements Runnable{
    private static int W = 500;
    private static int H = 200;
    private JPanel borderPanel;
    private JPanel Base;
    private JLabel lblUserID;
    private JLabel lblPassWord;
    private JTextField tfUser;
    private JTextField tfPass;
    private JButton btnLogin;

    public Login(String title){
        super(title);
    }

    @Override
    public void run() {
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(new Dimension(W,H));
        Base = new JPanel();
        this.getContentPane().add(Base,BorderLayout.CENTER);
        borderPanel = new JPanel(new GridBagLayout());
        borderPanel.setOpaque(true);
        borderPanel.setBorder(BorderFactory.createTitledBorder("Billboard Login"));

        this.getContentPane().add(borderPanel,BorderLayout.PAGE_START);

        lblUserID = new JLabel("User ID: ");
        lblPassWord = new JLabel("Pass Word: ");
        tfUser = new JTextField();
        tfUser.setPreferredSize(new Dimension(300,25));
        tfPass = new JTextField();
        tfPass.setPreferredSize(new Dimension(300,25));
        btnLogin = new JButton("Login");
        GridBagConstraints gcb = new GridBagConstraints();
        gcb.fill = GridBagConstraints.HORIZONTAL;
        gcb.gridx = 0;
        gcb.gridy = 0;
        borderPanel.add(lblUserID, gcb);

        gcb.gridx = 0;
        gcb.gridy = 1;
        borderPanel.add(tfUser, gcb);

        gcb.gridx = 0;
        gcb.gridy = 2;
        borderPanel.add(lblPassWord, gcb);


        gcb.gridx = 0;
        gcb.gridy = 3;
        borderPanel.add(tfPass, gcb);


        gcb.gridx = 2;
        gcb.gridy = 4;
        borderPanel.add(btnLogin, gcb);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Login successfully");
                SwingUtilities.invokeLater(new ControlPanel("BillboardControlPanel"));
                dispose();
            }
        });



    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Login("Login"));
    }

}