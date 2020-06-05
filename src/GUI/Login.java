package GUI;
import Request.LoginRequest;
import Server.SessionToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The type Login.
 */
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
    /**
     * The Output.
     */
    static ObjectOutputStream output;
    /**
     * The Input.
     */
    static ObjectInputStream input;

    /**
     * Instantiates a new Login.
     *
     * @param title the title
     */
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
        lblPassWord = new JLabel("Password: ");
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

        btnLogin.addActionListener(e -> {
            String userName = tfUser.getText();
            String password = tfPass.getText();
            try {
                System.out.println("Username: " +userName+ "\nPassword: " + password);
                output.writeObject(new LoginRequest(userName, password));
                output.flush();
                System.out.println("request sent!!!");

                System.out.println("[Login] Wait for Server's respond");
                Object o = input.readObject();
                if(o.equals("Fail")) {
                    System.out.println("Username or password is incorrect!");
                    JOptionPane.showMessageDialog(null,
                        "Username or password is incorrect!",
                        "Login fail",
                        JOptionPane.ERROR_MESSAGE);

                } else {
                    //TODO:
                    String privileges = (String) o;
                    ArrayList<String> listOfPrivileges = new ArrayList<>();
                    if(privileges.contains("Edit Users")){
                        listOfPrivileges.add("Edit Users");
                    }
                    if(privileges.contains("Create Billboard")){
                        listOfPrivileges.add("Create Billboard");
                    }
                    if(privileges.contains("Edit All Billboards")){
                        listOfPrivileges.add("Edit All Billboards");
                    }
                    if(privileges.contains("Schedule Billboard")){
                        listOfPrivileges.add("Schedule Billboard");
                    }
                    SessionToken token = new SessionToken(listOfPrivileges);

                    //TODO
                    JOptionPane.showMessageDialog(new JFrame(),"Login successfully!");
                    output.writeObject("Exit");
                    output.flush();
                    output.close();
                    input.close();
                    dispose();
                    ControlPanel.start(token);
                }
                System.out.println("End of a login session");
            } catch (IOException | ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
            //SwingUtilities.invokeLater(new ControlPanel("BillboardControlPanel"));
            //dispose();
        });

    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socketControlPanel = new Socket("localhost", 1234);

        output = new ObjectOutputStream(socketControlPanel.getOutputStream());
        input = new ObjectInputStream(socketControlPanel.getInputStream());
        output.writeObject("Login");
        output.flush();
        System.out.println(input.readObject());
        SwingUtilities.invokeLater(new Login("Login"));

    }

}
