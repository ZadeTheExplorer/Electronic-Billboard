package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JFrame implements ActionListener, Runnable {
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private JPanel pnlMenu;
    private JPanel pnlCenter;
    private JLabel lblName;
    private JButton btnBillboard;
    private JButton btnSchedule;
    private JButton btnNewBillBoard;
    private JButton btnUserManagement;

    public ControlPanel(String title){
        super(title);
    }

    private void createGUI() {
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        lblName = createLabel(Color.WHITE,"Bill Board Control Panel");
        pnlMenu = createPanel(Color.LIGHT_GRAY);
        pnlCenter = createPanel(Color.WHITE);
        btnBillboard = createButton("BillBoard");
        btnSchedule = createButton("Schedule");
        btnNewBillBoard = createButton("New Billboard");
        btnUserManagement = createButton("User Management");

        //Adjust the label in Center
        lblName.setPreferredSize(new Dimension( 900,100));
        lblName.setHorizontalAlignment(JLabel.CENTER);
        lblName.setVerticalTextPosition(JLabel.CENTER);
        lblName.setFont(new Font("Serif", Font.PLAIN, 34));

        //Adjust the label in main menu
        btnBillboard.setPreferredSize(new Dimension(200,100));
        btnBillboard.setBorderPainted(false);
        btnBillboard.setFont(new Font("Serif", Font.PLAIN, 18));
        btnBillboard.setContentAreaFilled(false);
        btnBillboard.setFocusPainted(false);
        btnBillboard.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnBillboard.setHorizontalTextPosition(SwingConstants.CENTER);
        btnBillboard.setIcon(new ImageIcon(getClass().getResource("/resources/billboardIcon.png")));

        btnSchedule.setPreferredSize(new Dimension(200,100));
        btnSchedule.setBorderPainted(false);
        btnSchedule.setFont(new Font("Serif", Font.PLAIN, 18));
        btnSchedule.setContentAreaFilled(false);
        btnSchedule.setFocusPainted(false);
        btnSchedule.setIcon(new ImageIcon(getClass().getResource("/resources/scheIcon.png")));
        btnSchedule.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSchedule.setHorizontalTextPosition(SwingConstants.CENTER);

        btnNewBillBoard.setPreferredSize(new Dimension(200,100));
        btnNewBillBoard.setBorderPainted(false);
        btnNewBillBoard.setFont(new Font("Serif", Font.PLAIN, 18));
        btnNewBillBoard.setContentAreaFilled(false);
        btnNewBillBoard.setFocusPainted(false);
        btnNewBillBoard.setIcon(new ImageIcon(getClass().getResource("/resources/newbbIcon.png")));
        btnNewBillBoard.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnNewBillBoard.setHorizontalTextPosition(SwingConstants.CENTER);

        btnUserManagement.setPreferredSize(new Dimension(200,100));
        btnUserManagement.setBorderPainted(false);
        btnUserManagement.setFont(new Font("Serif", Font.PLAIN, 18));
        btnUserManagement.setContentAreaFilled(false);
        btnUserManagement.setFocusPainted(false);
        btnUserManagement.setIcon(new ImageIcon(getClass().getResource("/resources/userIcon.png")));
        btnUserManagement.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnUserManagement.setHorizontalTextPosition(SwingConstants.CENTER);

        setLayout(new BorderLayout());


        this.getContentPane().add(lblName,BorderLayout.NORTH);
        this.getContentPane().add(pnlMenu,BorderLayout.WEST);
        this.getContentPane().add(pnlCenter,BorderLayout.CENTER);

        pnlMenu.setLayout(new GridBagLayout());
        pnlMenu.setPreferredSize(new Dimension(200, 40));

        //add components to grid
        GridBagConstraints constraints = new GridBagConstraints();
        //Defaults
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 100;
        constraints.weighty = 100;
        //Panel related code will go here


        addToPanel(pnlMenu, btnBillboard, constraints,2,0,3,1);
        addToPanel(pnlMenu, btnSchedule,constraints,2,1,3,1);
        addToPanel(pnlMenu, btnNewBillBoard,constraints,2,2,3,1);
        addToPanel(pnlMenu, btnUserManagement,constraints,2,3,3,1);

    }

    /**
     *
     * A convenience method to add a component to given grid bag
     * layout locations. Code due to Cay Horstmann
     *
     * @param c the component to add
     * @param constraints the grid bag constraints to use
     * @param x the x grid position
     * @param y the y grid position
     * @param w the grid width of the component
     * @param h the grid height of the component
     */
    private void addToPanel(JPanel jp,Component c, GridBagConstraints
            constraints,int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;
        jp.add(c, constraints);
    }

    private JLabel createLabel(Color c, String text){
        JLabel lbl = new JLabel();
        lbl.setBackground(c);
        lbl.setText(text);
        return lbl;
    }

    private JPanel createPanel(Color c) {
        //Create a JPanel object and store it in a local var
        JPanel jp = new JPanel();
        jp.setBackground(c);
        return jp;
        //set the background colour to that passed in c
        //Return the JPanel object
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


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ControlPanel("BillboardControlPanel"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBillboard){
            btnBillboard.setContentAreaFilled(true);
            btnNewBillBoard.setContentAreaFilled(false);
            btnSchedule.setContentAreaFilled(false);
            btnUserManagement.setContentAreaFilled(false);
            //pnlCenter.add();
        }
        else if(e.getSource() == btnNewBillBoard){
            btnBillboard.setContentAreaFilled(false);
            btnNewBillBoard.setContentAreaFilled(true);
            btnSchedule.setContentAreaFilled(false);
            btnUserManagement.setContentAreaFilled(false);
            //pnlCenter.add();

        }

        else if(e.getSource() == btnSchedule){
            btnBillboard.setContentAreaFilled(false);
            btnNewBillBoard.setContentAreaFilled(false);
            btnSchedule.setContentAreaFilled(true);
            btnUserManagement.setContentAreaFilled(false);
            //pnlCenter.add();

        }
        else if(e.getSource() == btnUserManagement){
            btnBillboard.setContentAreaFilled(false);
            btnNewBillBoard.setContentAreaFilled(false);
            btnSchedule.setContentAreaFilled(false);
            btnUserManagement.setContentAreaFilled(true);
            //pnlCenter.add();
        }
    }
}